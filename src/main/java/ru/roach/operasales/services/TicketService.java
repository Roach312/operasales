package ru.roach.operasales.services;

import ru.roach.operasales.model.event.IEvent;
import ru.roach.operasales.model.ticket.Ticket;

public class TicketService {

    private Ticket ticket;

    public TicketService(Ticket ticket){
        this.ticket = ticket;
    }

    public void buyTicket(double money){
        IEvent event = ticket.getEvent();
        if (event.getNumberOfSeat() > 0) {
            if (!ticket.isState()) {
                event.setNumberOfSeat(event.getNumberOfSeat() - 1);
                ticket.setPrice(money);
                ticket.setState(true);
                System.out.println("Вы успешно купили билет!\n" + event.toString() + "\n" +
                                   "Вы потратили: " + money);
            }else{
                ticket.setState(false);
                throw new IllegalStateException("This actual ticket!");
            }
        } else {
            ticket.setState(false);
            throw new IllegalStateException("Sold out!");
        }
    }

    public void returnTicket(){
        if (ticket.isState() == true) {
            IEvent event = ticket.getEvent();
            event.setNumberOfSeat(event.getNumberOfSeat() + 1);
            System.out.println("Вы успешно сдали свой билет!\n" + event.toString() + "\n" +
                               "Вы получили: " + ticket.getPrice());
             ticket.setPrice(0d);
            ticket.setState(false);
        } else {
            throw new IllegalStateException("Ticket cannot be returned!");
        }
    }
}
