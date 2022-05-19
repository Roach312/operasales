package ru.roach.operasales.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.roach.operasales.annotatians.NotifyMailBuyTicket;

import ru.roach.operasales.model.opera.Event;
import ru.roach.operasales.model.ticket.EventTicket;
import ru.roach.operasales.model.ticket.Ticket;
import ru.roach.operasales.repository.entities.TicketEntity;
import ru.roach.operasales.repository.interfaces.TicketRepository;


@Service
@Scope("singleton")
public class TicketServices {

    private OperaServices operaServices;
    private TicketRepository ticketRepository;

    @Autowired
    public TicketServices(OperaServices operaServices, TicketRepository ticketRepository) {
        this.operaServices = operaServices;
        this.ticketRepository = ticketRepository;
    }


    @NotifyMailBuyTicket
    @org.springframework.transaction.annotation.Transactional(
            propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.DEFAULT,
            timeout = 3,
            rollbackFor = {RuntimeException.class}
    )
    public void buyTicket(String eventName, double money, String mail) {
        Event event = operaServices.getEvent(eventName);
        TicketEntity ticket = ticketRepository.save(new TicketEntity(event, true, mail, money));
        operaServices.reSeatsEvent(event.getName(), event.getSeats() - 1);
    }

    @Transactional(
            propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.DEFAULT,
            timeout = 3,
            rollbackFor = {RuntimeException.class}
    )
    public void returnTicket(Long ticketNumber) {
        Ticket ticket = ticketRepository.getById(ticketNumber);
        if (ticket != null){
            String txtMsg = ticketRepository.returnTicket(false, ticketNumber) > 0 ? "Вы успешно сдали билет №" + ticketNumber
                    : "Не удалось сдать билет №" + ticketNumber;
            System.out.println(txtMsg);
        }
    }

}
