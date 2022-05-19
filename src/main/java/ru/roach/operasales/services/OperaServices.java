package ru.roach.operasales.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import ru.roach.operasales.annotatians.*;
import ru.roach.operasales.event.SeatsChangeEvent;
import ru.roach.operasales.model.opera.Event;
import ru.roach.operasales.model.opera.Opera;
import ru.roach.operasales.repository.entities.EventEntity;
import ru.roach.operasales.repository.interfaces.EventRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope("singleton")
public class OperaServices implements ApplicationContextAware {

    private EventRepository eventRepository;
    private ApplicationContext ctx;

    @Autowired
    public OperaServices (EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    public List<Opera> getAll() {
        return toDomain(eventRepository.findAll());
    }

    private static List<Opera> toDomain(List<EventEntity> entities) {
        return entities.stream()
                .map(OperaServices::toDomain)
                .collect(Collectors.toList());
    }

    private static Opera toDomain(EventEntity entity) {
        return new Opera(entity.getName(), entity.getInfo(), entity.getPegi(), entity.getSeats());
    }

    public Event getEvent(String name) {
        return eventRepository.getEventByName(name);
    }


    @Cacheable(value = "event.name", key = "#name")
    public EventEntity getEventEnt(String name) {
        return eventRepository.getEventByName(name);
    }

    @NotifyNewAnnonceEvent
    public void setEvent(String name, String info, int pegi, int seats) {
        System.out.println("Добавляем новую премьеру: " + name + "\n*******");
        eventRepository.save(new EventEntity(name, info, pegi, seats));
    }

    @Transactional(
            propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.DEFAULT,
            timeout = 3,
            rollbackFor = {RuntimeException.class}
    )
    public void removeEvent(String name) {
        String txtMsg = eventRepository.deleteByEventName(name) > 0 ? "Вы успешно удалили: " + name : "Не получилось удалить: " + name;
        System.out.println(txtMsg);
    }

    @NotifyChangeEvent
    @Transactional(
            propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.DEFAULT,
            timeout = 3,
            rollbackFor = {RuntimeException.class}
    )
    public void reNameEvent(String oldName, String newName) {
        String txtMsg = eventRepository.updateEventName(oldName, newName) > 0 ? "Вы успешно поменяли имя с: " + oldName + " на: " + newName
                : "Не удалось поменять имя с: " + oldName + " на: " + newName;
        System.out.println(txtMsg);
    }

    @NotifyChangeEvent
    @Transactional(
            propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.DEFAULT,
            timeout = 3,
            rollbackFor = {RuntimeException.class}
    )
    public void reInfoEvent(String name, String info) {
        String txtMsg = eventRepository.updateEventInfo(name, info) > 0 ? "Вы успешно поменяли информацию мероприятия: " + name : "Не удалось поменть информацию мероприятия: " + name;
        System.out.println(txtMsg);
    }

    @NotifyChangeEvent
    @Transactional(
            propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.DEFAULT,
            timeout = 3,
            rollbackFor = {RuntimeException.class}
    )
    public void rePegiEvent(String name, int pegi) {
        String txtMsg = eventRepository.updateEventPegi(name, pegi) > 0 ? "Вы успешно поменяли возрастное ограничение мероприятия: " + name
                : "Не удалось поменять возрастное ограничение мероприятия: " + name;
        System.out.println(txtMsg);
    }

    @Transactional(
            propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.DEFAULT,
            timeout = 3,
            rollbackFor = {RuntimeException.class}
    )
    public void reSeatsEvent(String name, int seats) {
        String txtMsg = eventRepository.updateEventSeats(name, seats) > 0 ? "Вы успешно поменяли кол-во мест мероприятия: " + name
                : "Не удалось поменять кол-во мест мероприятия: " + name;
        System.out.println(txtMsg);
        ctx.publishEvent(
                new SeatsChangeEvent(
                        new SeatsChangeEvent.Info(name, seats)
                )
        );
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }
}
