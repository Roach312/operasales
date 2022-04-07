package ru.roach.operasales.model.event;

public interface IEvent {
    String getEventName();
    void setEventName(String eventName);

    StringBuilder getInfoEvent();
    void setInfoEvent(StringBuilder infoEvent);

    int getPegi();
    void setPegi(int pegi);

    int getNumberOfSeat();
    void setNumberOfSeat(int numberOfSeat);
}
