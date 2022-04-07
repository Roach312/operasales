package ru.roach.operasales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.roach.operasales.services.*;
import ru.roach.operasales.model.event.*;

@SpringBootApplication
public class OperasalesApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(OperasalesApplication.class, args);
        System.out.println("----Бин оперы----");
        System.out.println(ctx.getBean(Event.class).getEventName());
        System.out.println("----Опера-сервис----");
        ctx.getBean(EventService.class).addEvent("Травиата", new StringBuilder("Опера в трех действиях (исполняется с одним антрактом). Либретто Франческо Мария Пьяве по мотивам романа Александра Дюма-сына «Дама с камелиями»"),
                16, 500);
        ctx.getBean(EventService.class).getFullInfoEvent("Травиата");
        ctx.getBean(EventService.class).getFullInfoEvent("Кармен");
        System.out.println("----Опера-сервис меняем премьеру----");
        ctx.getBean(EventService.class).reNameEvent("Травиата", "Алеко");
        ctx.getBean(EventService.class).reInfoEvent("Алеко", new StringBuilder("Трудно представить, но Сергею Рахманинову было всего 19 лет, когда он, выпускник консерватории, за 17 дней создал свою дипломную работу «Алеко», которой суждено было войти в историю мировой оперы на равных с шедеврами признанных мастеров!"));
        ctx.getBean(EventService.class).rePegiEvent("Алеко", 18);
        ctx.getBean(EventService.class).reSeatsEvent("Алеко", 2_000);
        ctx.getBean(EventService.class).getFullInfoEvent("Алеко");
        System.out.println("----Покупаем билет----");
        ctx.getBean(TicketService.class).buyTicket(2000d);
        System.out.println("----Возвращаем билет билет----");
        ctx.getBean(TicketService.class).returnTicket();
        System.out.println("----Пытаемся ещё раз вернуть билет----");
        ctx.getBean(TicketService.class).returnTicket();
    }


}
