package ru.roach.operasales.services;

import ru.roach.operasales.model.event.Event;
import ru.roach.operasales.model.event.IEvent;

import java.util.HashMap;
import java.util.Map;

public class EventService {
    private Map<String, IEvent> events;

    public void addEvent(String eventName, StringBuilder info, int pegi, int numberOfSeat) {
        IEvent event = new Event(eventName, info, pegi, numberOfSeat);
        if (events == null) {
            events = new HashMap<String, IEvent>() {{
                put(eventName, event);
            }};
        } else {
            events.put(eventName, event);
        }

    }

    public void addEvent(IEvent event) {
        if (events == null) {
            events = new HashMap<String, IEvent>() {{
                put(event.getEventName(), event);
            }};
        } else {
            events.put(event.getEventName(), event);
        }

    }

    public boolean deleteEvent(String eventName) {
        boolean result = false;
        if (events.containsKey(eventName)) {
            events.remove(eventName);
            result = true;
        }
        return result;
    }

    public void getFullInfoEvent(String eventName) {
        IEvent event = events.get(eventName);
        System.out.println(event.toString());
    }

    public boolean reNameEvent(String oldEventName, String newEventName) {
        boolean result = false;
        IEvent event = events.get(oldEventName);
        if (event != null) {
            event.setEventName(newEventName);
            events.remove(oldEventName);
            events.put(newEventName, event);
            result = true;
        }
        return result;
    }

    public boolean reInfoEvent(String eventName, StringBuilder newInfo) {
        boolean result = false;
        IEvent event = events.get(eventName);
        if (event != null) {
            event.setInfoEvent(newInfo);
            events.replace(eventName, event);
            result = true;
        }
        return result;
    }

    public boolean rePegiEvent(String eventName, int newPegi) {
        boolean result = false;
        IEvent event = events.get(eventName);
        if (event != null) {
            event.setPegi(newPegi);
            events.replace(eventName, event);
            result = true;
        }
        return result;
    }

    public boolean reSeatsEvent(String eventName, int newNumberOfSeat) {
        boolean result = false;
        IEvent event = events.get(eventName);
        if (event != null) {
            event.setNumberOfSeat(newNumberOfSeat);
            events.replace(eventName, event);
            result = true;
        }
        return result;
    }

}
