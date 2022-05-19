package ru.roach.operasales.repository.interfaces;

import org.springframework.data.jpa.repository.*;
import ru.roach.operasales.repository.entities.EventEntity;

import javax.persistence.LockModeType;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

    long deleteByEventName(String eventName);

    @Query(name = "event.findEventByName")
    @EntityGraph(value = "eventWithTickets")
    EventEntity getEventByName(String eventNamePattern);

    @Modifying
    @Query(name = "event.reName")
    int updateEventName(String oldNamePattern, String newNamePattern);

    @Modifying
    @Query(name = "event.reInfo")
    int updateEventInfo(String eventNamePattern, String newInfoPattern);

    @Modifying
    @Query(name = "event.rePegi")
    int updateEventPegi(String eventNamePattern, int newPegiPattern);

    @Modifying
    @Query(name = "event.reSeats")
    int updateEventSeats(String eventNamePattern, int newSeatsPattern);
}
