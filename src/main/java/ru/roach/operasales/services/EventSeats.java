package ru.roach.operasales.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ru.roach.operasales.event.SeatsChangeEvent;
import ru.roach.operasales.model.ticket.Ticket;
import ru.roach.operasales.services.mail.EmailNotifier;

@Component
public class EventSeats implements ApplicationListener<SeatsChangeEvent> {

    @Override
    public void onApplicationEvent(SeatsChangeEvent event) {
        SeatsChangeEvent.Info info = (SeatsChangeEvent.Info) event.getSource();

        //Рассылка всем мэйлам кто купил билеты на данное мероприятие
        for (Object obj : TicketServices.getTickets()) {
            Ticket ticket = (Ticket) obj;
            if (ticket.getEvent().getName().equals(info.getEventName())) {
                EmailNotifier.sendSimpleEmail(ticket.getMail(),
                        "Мы изменили кол-во мест",
                        "Произошла замена кол-ва мест на мероприятии: " + info.getEventName() +
                                "\nНовое кол-во мест: " + info.getNewSeatsValue());
            }
        }
    }
}
