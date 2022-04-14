package ru.roach.operasales.model.ticket;

import ru.roach.operasales.model.opera.Event;

public interface Ticket {
    Event getEvent();
    void setEvent(Event event);

    boolean getState();
    void setState(boolean state);

    double getMoney();
    void setMoney(double money);

}
