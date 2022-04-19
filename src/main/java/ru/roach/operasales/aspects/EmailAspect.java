package ru.roach.operasales.aspects;

import com.sun.xml.internal.txw2.IllegalSignatureException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.roach.operasales.model.ticket.Ticket;
import ru.roach.operasales.services.OperaServices;
import ru.roach.operasales.services.TicketServices;
import ru.roach.operasales.services.mail.EmailNotifier;


@Component
@Aspect
public class EmailAspect {
    @Pointcut("@annotation(ru.roach.operasales.annotatians.NotifyMailBuyTicket)")
    public void sendEmailSuccessBuyTicket() {
    }

    @Pointcut("@annotation(ru.roach.operasales.annotatians.NotifyNewAnnonceEvent)")
    public void sendEmailNewAnnounceEvent() {
    }

    @Pointcut("@annotation(ru.roach.operasales.annotatians.NotifyChangeEvent)")
    public void sendEmailChangeEvent() {
    }

    @AfterReturning("sendEmailSuccessBuyTicket()")
    public void afterSuccessBuyTicket(JoinPoint point) {
        Object[] signatureArgs = point.getArgs();
        EmailNotifier.sendSimpleEmail(signatureArgs[2].toString(),
                "Покупка билета на: " + signatureArgs[0],
                "Вы успешно преобрели билет на мероприятие: " + signatureArgs[0] +
                        "\nВы потратили: " + signatureArgs[1]);
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

    @AfterReturning("sendEmailChangeEvent()")
    public void afterSuccessChangeEvent(JoinPoint point) {
        //Не говнокод, а спецом эксперементировал как вытаскивать класс, аргументы и т.д. :)
        if (point.getTarget() instanceof OperaServices) {
            String methodName = point.getSignature().getName();
            Object[] signatureArgs = point.getArgs();
            String subject = null, message = null, event = null;

            TicketServices ticketServices = new TicketServices(new OperaServices());
            switch (methodName) {
                case ("reNameEvent"):
                    event = signatureArgs[1].toString();
                    subject = "Изменилось название мероприятия!";
                    message = "Мы изменили название мероприятия с: " + signatureArgs[0] + " на: " + signatureArgs[1];
                    break;
                case ("reInfoEvent"):
                    event = signatureArgs[1].toString();
                    subject = "Изменилась информация о мероприятии!";
                    message = "Мы изменили инфо о мероприятия: " + signatureArgs[0] +
                            "\nНовая информация: " + signatureArgs[1];
                    break;
                case ("rePegiEvent"):
                    event = signatureArgs[1].toString();
                    subject = "Изменилось возрастное ограничение мероприятия!";
                    message = "Мы изменили возрастное ограничение мероприятия: " + signatureArgs[0] +
                            "\nНовое возрастное ограничение: " + signatureArgs[1];
                    break;
                default:
                    throw new IllegalSignatureException("Неверно выбранный метод для инжекта аспекта!");
            }

            //Рассылка всем мэйлам кто купил билеты на данное мероприятие
            for (Object obj : TicketServices.getTickets()){
                Ticket ticket = (Ticket) obj;
                if (ticket.getEvent().getName().equals(event)){
                    EmailNotifier.sendSimpleEmail(ticket.getMail(), subject, message);
                }
            }
        } else {
            throw new IllegalSignatureException("Неверно выбранный класс для инжекта аспекта!");
        }
    }


}
