package ru.roach.operasales.repository.entities;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import ru.roach.operasales.model.opera.Event;
import ru.roach.operasales.model.ticket.Ticket;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
@NamedQueries({
        @NamedQuery(name = "ticket.return", query = "update TicketEntity te set te.state = :newStatePattern where te.id = :idTicketPattern")
})
public class TicketEntity implements Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_seq")
    @SequenceGenerator(name = "ticket_seq", allocationSize = 1)
    private Long id;

    @JoinColumn(name = "event_id")
    @ManyToOne
    private EventEntity event;

    @Column(name = "state")
    private boolean state;

    @Column(name = "mail")
    private String mail;

    @Column(name = "price")
    private double price;

    public TicketEntity(){}

    public TicketEntity(Event event, boolean state, String mail, double price){
        this.event = (EventEntity) event;
        this.state = state;
        this.mail = mail;
        this.price = price;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public Event getEvent() {
        return event;
    }

    @Override
    public void setEvent(Event event) {
        this.event = (EventEntity) event;
    }

    @Override
    public boolean getState() {
        return state;
    }

    @Override
    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public double getMoney() {
        return price;
    }

    @Override
    public void setMoney(double money) {
        this.price = price;
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Название мероприятия: " + getEvent().toString() + "\n" +
                "Валидность билета: " + (getState() ? "Валидный" : "Билет был сдан!") + "\n" +
                "Ваша почта для уведомлений: " + getMail() + "\n" +
                "Стоймость: " + getMoney();
    }
}
