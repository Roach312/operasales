package ru.roach.operasales.services;

import org.springframework.stereotype.Service;
import ru.roach.operasales.annotatians.*;
import ru.roach.operasales.model.opera.Opera;
import ru.roach.operasales.model.opera.Event;

import java.util.Map;
import java.util.HashMap;


@Service
public class OperaServices {

    private Map<String, Event> events = new HashMap<>();

    public Event getEvent(String name) {
        return events.get(name);
    }

    @NotifyNewAnnonceEvent
    public void setEvent(String name, StringBuilder info, int pegi, int seats) {
        System.out.println("Добавляем новую премьеру: " + name + "\n*******");
        events.put(name, new Opera(name, info, pegi, seats));
    }

    public void removeEvent(String name) {
        events.remove(name);
    }

    public void viewEvent(String name) {
        System.out.println(getEvent(name).toString() + "\n-------");
    }

    public void viewAllEvents() {
        events.forEach((k, v) -> System.out.println(v.toString() + "\n-------"));
    }

    @NotifyChangeEvent
    public void reNameEvent(String oldName, String newName) {
        Event event = getEvent(oldName);
        if (event == null) {
            throw new IllegalArgumentException("Такой премьеры не существует!");
        }
        event.setName(newName);
        events.remove(oldName);
        events.put(newName, event);
    }

    @NotifyChangeEvent
    public void reInfoEvent(String name, StringBuilder info) {
        Event event = getEvent(name);
        if (event == null) {
            throw new IllegalArgumentException("Такой премьеры не существует!");
        }
        event.setInfo(info);
        events.replace(name, event);
    }

    @NotifyChangeEvent
    public void rePegiEvent(String name, int pegi) {
        Event event = getEvent(name);
        if (event == null) {
            throw new IllegalArgumentException("Такой премьеры не существует!");
        }
        event.setPegi(pegi);
        events.replace(name, event);
    }

    public void reSeatsEvent(String name, int seats) {
        Event event = getEvent(name);
        if (event == null) {
            throw new IllegalArgumentException("Такой премьеры не существует!");
        }
        event.setSeats(seats);
        events.replace(name, event);
    }


}
