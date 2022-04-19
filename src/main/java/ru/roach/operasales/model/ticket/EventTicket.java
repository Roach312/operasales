package ru.roach.operasales.model.ticket;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.roach.operasales.model.opera.Event;

@Scope("prototype")
public class EventTicket implements Ticket {

    private Event event;
    private boolean state = false;
    private double money;
    private String mail;

    public EventTicket(Event event, boolean state, double money, String mail) {
        setEvent(event);
        setState(state);
        setMoney(money);
        setMail(mail);
    }

    @Override
    public Event getEvent() {
        return event;
    }

    @Override
    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public boolean getState() {
        return state;
    }

    @Override
    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public double getMoney() {
        return money;
    }

    @Override
    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public void setMail(String mail) {
        this.mail = mail;
    }

}
