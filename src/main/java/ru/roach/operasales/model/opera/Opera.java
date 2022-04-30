package ru.roach.operasales.model.opera;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class Opera implements Event {

    private String eventName;
    private String eventInfo;
    private int pegi;
    private int seats;

    public Opera(String eventName, String eventInfo, int pegi, int seats) {
        setName(eventName);
        setInfo(eventInfo);
        setPegi(pegi);
        setSeats(seats);
    }

    @Override
    public String getName() {
        return eventName;
    }

    @Override
    public void setName(String eventName) {
        if (eventName.equals("")) {
            throw new IllegalArgumentException("Необходимо задать название мероприятия!");
        }
        this.eventName = eventName;
    }

    @Override
    public String getInfo() {
        return eventInfo;
    }

    @Override
    public void setInfo(String eventInfo) {
        this.eventInfo = eventInfo;
    }

    @Override
    public int getPegi() {
        return pegi;
    }

    @Override
    public void setPegi(int pegi) {
        if (pegi < 0) {
            throw new IllegalArgumentException("Некорректное возрастное ограничение мероприятия!");
        } else if (pegi > 21) {
            throw new IllegalArgumentException("Вы там что хотите устроить?!");
        }
        this.pegi = pegi;
    }

    @Override
    public int getSeats() {
        return seats;
    }

    @Override
    public void setSeats(int seats) {
        if (seats < 0 || seats > 1_000_000_000) {
            throw new IllegalArgumentException("Некорректное кол-во мест на мероприятии!");
        }
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Название мероприятия: " + getName() + "\n" +
                "Краткая информация: " + getInfo() + "\n" +
                "Возрастное ограничение: " + getPegi() + "\n" +
                "Кол-во мест: " + getSeats();
    }
}
