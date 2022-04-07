package ru.roach.operasales.config;

import org.springframework.context.annotation.*;
import ru.roach.operasales.model.event.Event;
import ru.roach.operasales.model.event.IEvent;
import ru.roach.operasales.services.EventService;

@Configuration
@Import(TicketConfig.class)
public class EventConfig {

    @Bean
    @Profile("prod")
    public IEvent event() {
        return new Event("Кармен", new StringBuilder("Всемирно известная опера Бизе в новом прочтении режиссера Максима Диденко. Сценическая композиция по мотивам новеллы Проспера Мериме и музыки Жоржа Бизе."), 12, 1_000);
    }

    @Bean
    @Profile("dev")
    public IEvent eventDev() {
        return new Event("Евгений онегин", new StringBuilder("Test"), 5, 10);
    }

    @Bean
    public EventService eventService(IEvent event) {
        EventService eventService = new EventService();
        eventService.addEvent(event);
        return eventService;
    }


}
