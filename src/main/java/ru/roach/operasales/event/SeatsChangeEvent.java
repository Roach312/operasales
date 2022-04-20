package ru.roach.operasales.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

public class SeatsChangeEvent extends ApplicationEvent {

    public SeatsChangeEvent(Info Info) {
        super(Info);
    }

    public static class Info{
        private String eventName;
        private int newSeatsValue;

        public Info(String eventName, int newSeatsValue) {
            this.eventName = eventName;
            this.newSeatsValue = newSeatsValue;
        }

        public String getEventName() {
            return eventName;
        }

        public void setEventName(String eventName) {
            this.eventName = eventName;
        }

        public int getNewSeatsValue() {
            return newSeatsValue;
        }

        public void setNewSeatsValue(int newSeatsValue) {
            this.newSeatsValue = newSeatsValue;
        }
    }

}
