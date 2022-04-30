package ru.roach.operasales.aspects;

import com.sun.xml.internal.txw2.IllegalSignatureException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.roach.operasales.model.ticket.Ticket;
import ru.roach.operasales.repository.entities.EventEntity;
import ru.roach.operasales.repository.entities.TicketEntity;
import ru.roach.operasales.services.OperaServices;
import ru.roach.operasales.services.mail.EmailNotifier;

import java.util.Collection;


@Component
@Aspect
public class EmailAspect {

    private OperaServices operaServices;

    @Autowired
    public EmailAspect(OperaServices operaServices) {
        this.operaServices = operaServices;
    }

    @Pointcut("@annotation(ru.roach.operasales.annotatians.NotifyMailBuyTicket)")
    public void sendEmailSuccessBuyTicket() {
    }

    @AfterReturning("sendEmailSuccessBuyTicket()")
    public void afterSuccessBuyTicket(JoinPoint point) {
        Object[] signatureArgs = point.getArgs();
        EmailNotifier.sendSimpleEmail(signatureArgs[2].toString(),
                "Покупка билета на: " + signatureArgs[0],
                "Вы успешно преобрели билет на мероприятие: " + signatureArgs[0] +
                        "\nВы потратили: " + signatureArgs[1]);
    }

    @Pointcut("@annotation(ru.roach.operasales.annotatians.NotifyNewAnnonceEvent)")
    public void sendEmailNewAnnounceEvent() {
    }

    @AfterReturning("sendEmailNewAnnounceEvent()")
    public void afterSuccessNewAnnounceEvent(JoinPoint point) {
        Object[] signatureArgs = point.getArgs();
        EmailNotifier.sendSimpleEmail("all_users@mail.ru",
                "Аннонс нового мероприятия: " + signatureArgs[0],
                "Представляем Вашему вниманию новое мероприятие: " + signatureArgs[0] +
                        "\n" + signatureArgs[1] +
                        "\nВозростное ограничение: " + signatureArgs[2] +
                        "\nКол-во мест: " + signatureArgs[3]);
    }

    @Pointcut("@annotation(ru.roach.operasales.annotatians.NotifyChangeEvent)")
    public void sendEmailChangeEvent() {
    }

    @AfterReturning("sendEmailChangeEvent()")
    public void afterSuccessChangeEvent(JoinPoint point) {
        //Не говнокод, а спецом эксперементировал как вытаскивать класс, аргументы и т.д. :)
        if (point.getTarget() instanceof OperaServices) {
            String methodName = point.getSignature().getName();
            Object[] signatureArgs = point.getArgs();
            String subject = null, message = null, event = null;

            switch (methodName) {
                case ("reNameEvent"):
                    event = signatureArgs[1].toString();
                    subject = "Изменилось название мероприятия!";
                    message = "Мы изменили название мероприятия с: " + signatureArgs[0] + " на: " + event;
                    break;
                case ("reInfoEvent"):
                    event = signatureArgs[0].toString();
                    subject = "Изменилась информация о мероприятии!";
                    message = "Мы изменили инфо о мероприятии: " + event +
                            "\nНовая информация: " + signatureArgs[1];
                    break;
                case ("rePegiEvent"):
                    event = signatureArgs[0].toString();
                    subject = "Изменилось возрастное ограничение мероприятия!";
                    message = "Мы изменили возрастное ограничение мероприятия: " + event +
                            "\nНовое возрастное ограничение: " + signatureArgs[1];
                    break;
                default:
                    throw new IllegalSignatureException("Неверно выбранный метод для инжекта аспекта!");
            }

            EventEntity eventEntity = (EventEntity) operaServices.getEvent(event);
            for (Ticket ticket : eventEntity.getTicket()) {
                EmailNotifier.sendSimpleEmail(ticket.getMail(), subject, message);
            }

        } else {
            throw new IllegalSignatureException("Неверно выбранный класс для инжекта аспекта!");
        }
    }


}
