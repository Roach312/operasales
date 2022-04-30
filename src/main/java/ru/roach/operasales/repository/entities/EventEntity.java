package ru.roach.operasales.repository.entities;


import org.hibernate.annotations.CacheConcurrencyStrategy;
import ru.roach.operasales.model.opera.Event;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "event")
@NamedQueries({
        @NamedQuery(name = "event.findEventByName", query = "select ee from EventEntity ee where ee.eventName = :eventNamePattern"),
        @NamedQuery(name = "event.reName", query = "update EventEntity ee set ee.eventName = :newNamePattern where ee.eventName = :oldNamePattern"),
        @NamedQuery(name = "event.reInfo", query = "update EventEntity ee set ee.eventInfo = :newInfoPattern where ee.eventName = :eventNamePattern"),
        @NamedQuery(name = "event.rePegi", query = "update EventEntity ee set ee.pegi = :newPegiPattern where ee.eventName = :eventNamePattern"),
        @NamedQuery(name = "event.reSeats", query = "update EventEntity ee set ee.seats = :newSeatsPattern where ee.eventName = :eventNamePattern")
})
@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "eventWithTickets",
                attributeNodes = {
                        @NamedAttributeNode("id"),
                        @NamedAttributeNode("eventName"),
                        @NamedAttributeNode("eventInfo"),
                        @NamedAttributeNode("pegi"),
                        @NamedAttributeNode("seats"),
                        @NamedAttributeNode(value = "ticket", subgraph = "eventTicket")
                },
                subgraphs = {
                        @NamedSubgraph(name = "eventTicket", attributeNodes = {
                                @NamedAttributeNode("id"),
                                @NamedAttributeNode("state"),
                                @NamedAttributeNode("mail"),
                                @NamedAttributeNode("price")
                        })
                }
        )
})
@org.hibernate.annotations.Cache(region = "event.id", usage = CacheConcurrencyStrategy.READ_ONLY)
public class EventEntity implements Event{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
    @SequenceGenerator(name = "event_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", length = 100)
    private String eventName;

    @Column(name = "info", length = 500)
    private String eventInfo;

    @Column(name = "pegi")
    private int pegi;

    @Column(name = "seats")
    private int seats;

    @OneToMany(mappedBy = "event")
    private Collection<TicketEntity> ticket;

    public EventEntity() {}

    public EventEntity(String eventName, String eventInfo, int pegi, int seats) {
        setName(eventName);
        setInfo(eventInfo);
        setPegi(pegi);
        setSeats(seats);
    }

    @Override
    public String getName() {
        return eventName;
    }

    @Override
    public void setName(String eventName) {
        if (eventName.equals("")) {
            throw new IllegalArgumentException("Необходимо задать название мероприятия!");
        }
        this.eventName = eventName;
    }

    @Override
    public String getInfo() {
        return eventInfo;
    }

    @Override
    public void setInfo(String eventInfo) {
        this.eventInfo = eventInfo;
    }

    @Override
    public int getPegi() {
        return pegi;
    }

    @Override
    public void setPegi(int pegi) {
        if (pegi < 0) {
            throw new IllegalArgumentException("Некорректное возрастное ограничение мероприятия!");
        } else if (pegi > 21) {
            throw new IllegalArgumentException("Вы там что хотите устроить?!");
        }
        this.pegi = pegi;
    }

    @Override
    public int getSeats() {
        return seats;
    }

    @Override
    public void setSeats(int seats) {
        if (seats < 0 || seats > 1_000_000_000) {
            throw new IllegalArgumentException("Некорректное кол-во мест на мероприятии!");
        }
        this.seats = seats;
    }

    public Collection<TicketEntity> getTicket() {
        return this.ticket;
    }

    @Override
    public String toString() {
        return "Название мероприятия: " + getName() + "\n" +
                "Краткая информация: " + getInfo() + "\n" +
                "Возрастное ограничение: " + getPegi() + "\n" +
                "Кол-во мест: " + getSeats();
    }
}
