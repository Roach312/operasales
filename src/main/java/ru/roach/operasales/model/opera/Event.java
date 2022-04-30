package ru.roach.operasales.model.opera;

public interface Event {
    String getName();
    void setName(String eventName);

    String getInfo();
    void setInfo(String eventInfo);

    int getPegi();
    void setPegi(int pegi);

    int getSeats();
    void setSeats(int numberOfSeat);

}
