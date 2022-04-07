package ru.roach.operasales.model.event;

public class Event implements IEvent {
    private String eventName;
    private StringBuilder infoEvent;
    private int pegi;
    private int numberOfSeat;

    public Event(String eventName, StringBuilder infoEvent, int pegi, int numberOfSeat) {
        setEventName(eventName);
        setInfoEvent(infoEvent);
        setPegi(pegi);
        setNumberOfSeat(numberOfSeat);
    }

    @Override
    public String getEventName() {
        return eventName;
    }

    @Override
    public void setEventName(String eventName) {
        if (eventName.equals("")) {
            throw new IllegalArgumentException("Необходимо задать название мероприятия!");
        }
        this.eventName = eventName;
    }

    @Override
    public StringBuilder getInfoEvent() {
        return infoEvent;
    }

    @Override
    public void setInfoEvent(StringBuilder infoEvent) {
        this.infoEvent = infoEvent;
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
    public int getNumberOfSeat() {
        return numberOfSeat;
    }

    @Override
    public void setNumberOfSeat(int numberOfSeat) {
        if (numberOfSeat < 0 || numberOfSeat > 1_000_000_000) {
            throw new IllegalArgumentException("Некорректное кол-во мест на мероприятии!");
        }
        this.numberOfSeat = numberOfSeat;
    }

    @Override
    public String toString() {
        return "Название мероприятия: " + getEventName() + "\n" +
                "Краткая информация: " + getInfoEvent() + "\n" +
                "Возрастное ограничение: " + getPegi() + "\n" +
                "Кол-во мест: " + getNumberOfSeat();
    }

    public void consoleOut() {
        System.out.println(toString());
    }
}
