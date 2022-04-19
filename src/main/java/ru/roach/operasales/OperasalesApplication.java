package ru.roach.operasales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.roach.operasales.services.OperaServices;
import ru.roach.operasales.services.TicketServices;


@SpringBootApplication
public class OperasalesApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(OperasalesApplication.class, args);
        ctx.getBean(OperaServices.class).setEvent("Травиата", new StringBuilder("Опера в трех действиях (исполняется с одним антрактом). Либретто Франческо Мария Пьяве по мотивам романа Александра Дюма-сына «Дама с камелиями»"),
                16, 500);
        ctx.getBean(OperaServices.class).setEvent("Алеко", new StringBuilder("Трудно представить, но Сергею Рахманинову было всего 19 лет, когда он, выпускник консерватории, за 17 дней создал свою дипломную работу «Алеко», которой суждено было войти в историю мировой оперы на равных с шедеврами признанных мастеров!"), 18, 2_000);

        ctx.getBean(OperaServices.class).viewAllEvents();

        ctx.getBean(TicketServices.class).buyTicket("Травиата", 2000, "exampe_user@mail.ru");
        ctx.getBean(TicketServices.class).buyTicket("Травиата", 4000, "NEW_User121@mymail.ru");
        ctx.getBean(TicketServices.class).returnTicket("Травиата", 1);

        ctx.getBean(OperaServices.class).reNameEvent("Травиата", "TEST");
        ctx.getBean(OperaServices.class).reSeatsEvent("TEST", 10_000);

        ctx.getBean(OperaServices.class).viewEvent("TEST");
    }


}
