package ru.roach.operasales.services;

import org.springframework.stereotype.Service;
import ru.roach.operasales.model.opera.Event;
import ru.roach.operasales.model.ticket.EventTicket;
import ru.roach.operasales.model.ticket.Ticket;

import java.util.LinkedList;
import java.util.List;

@Service
public class TicketServices {

    private OperaServices operaServices;
    private List<Ticket> tickets = new LinkedList<>();

    public TicketServices(OperaServices operaServices) {
        this.operaServices = operaServices;
    }

    public void buyTicket(String eventName, double money) {
        Event event = operaServices.getEvent(eventName);
        if (event.getSeats() == 0) {
            throw new IllegalStateException("Мест больше нет!");
        }
        event.setSeats(event.getSeats() - 1);
        tickets.add(new EventTicket(event, true, money));
        System.out.println("Вы успешно купили билет на: " + event.getName() +
                "\nНомер вашего билета: " + tickets.size() +
                "\nПотрачено денег: " + money +
                "\nОсталось мест:" + event.getSeats() +
                "\n#######");
    }

    public void returnTicket(String eventName, int ticketNumber) {
        if (eventName.equals(tickets.get(ticketNumber - 1).getEvent().getName())) {
            Ticket ticket = tickets.get(ticketNumber - 1);
            if (ticket.getState() == false){
                throw new IllegalStateException("Вы уже сдавали этот билет!");
            }
            ticket.setState(false);
            double money = ticket.getMoney();
            ticket.setMoney(money - money);
            Event event = ticket.getEvent();
            event.setSeats(event.getSeats() + 1);
            System.out.println("Вы успешно сдали билет на: " + event.getName() +
                    "\nВозвращено денег: " + money +
                    "\nОсталось мест:" + event.getSeats() +
                    "\n#######");
        } else {
            throw new IllegalArgumentException("Введите правильно премьеру!");
        }

    }

}
