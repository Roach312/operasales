package ru.roach.operasales.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.roach.operasales.model.event.IEvent;
import ru.roach.operasales.model.ticket.Ticket;
import ru.roach.operasales.services.TicketService;

@Configuration
public class TicketConfig {

    @Bean

    public Ticket ticket(IEvent event) {
        return new Ticket(event);
    }

    @Bean
    public TicketService ticketService(Ticket ticket) {
        return new TicketService(ticket);
    }
}
