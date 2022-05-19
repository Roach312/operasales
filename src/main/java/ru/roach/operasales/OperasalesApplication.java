package ru.roach.operasales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import ru.roach.operasales.model.ticket.Ticket;
import ru.roach.operasales.services.OperaServices;
import ru.roach.operasales.services.TicketServices;


@SpringBootApplication
@EntityScan({"ru.roach.operasales.repository.entities"})
@EnableCaching
public class OperasalesApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperasalesApplication.class, args);
    }


}
