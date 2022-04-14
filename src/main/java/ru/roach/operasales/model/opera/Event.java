package ru.roach.operasales.model.opera;

public interface Event {
    String getName();
    void setName(String eventName);

    StringBuilder getInfo();
    void setInfo(StringBuilder eventInfo);

    int getPegi();
    void setPegi(int pegi);

    int getSeats();
    void setSeats(int numberOfSeat);

}
