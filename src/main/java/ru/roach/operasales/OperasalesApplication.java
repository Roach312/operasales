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
        final ConfigurableApplicationContext ctx = SpringApplication.run(OperasalesApplication.class, args);

        ctx.getBean(OperaServices.class).setEvent("Травиата", "Опера в трех действиях (исполняется с одним антрактом). Либретто Франческо Мария Пьяве по мотивам романа Александра Дюма-сына «Дама с камелиями»",16, 500);

        ctx.getBean(OperaServices.class).setEvent("Алеко", "Трудно представить, но Сергею Рахманинову было всего 19 лет, когда он, выпускник консерватории, за 17 дней создал свою дипломную работу «Алеко», которой суждено было войти в историю мировой оперы на равных с шедеврами признанных мастеров!", 18, 3000);

        ctx.getBean(TicketServices.class).buyTicket("Алеко", 1000, "new_mail@gmail.com");

        ctx.getBean(OperaServices.class).reNameEvent("Травиата", "Травиата2");



        ctx.getBean(OperaServices.class).getEventEnt("Травиата");
        ctx.getBean(OperaServices.class).getEventEnt("Травиата");
        ctx.getBean(OperaServices.class).getEventEnt("Травиата");
        ctx.getBean(OperaServices.class).getEventEnt("Травиата");
        ctx.getBean(OperaServices.class).getEventEnt("Травиата");
        ctx.getBean(OperaServices.class).getEventEnt("Травиата");

        ctx.getBean(TicketServices.class).returnTicket(1L);

    }


}
