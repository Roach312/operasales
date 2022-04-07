package ru.roach.operasales.model.ticket;

import ru.roach.operasales.model.event.IEvent;

public class Ticket {
    private double price;
    private boolean state;
    private IEvent event;

    public Ticket (IEvent event){
        this.event = event;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public IEvent getEvent() {
        return event;
    }

    public void setEvent(IEvent event) {
        this.event = event;
    }
}
