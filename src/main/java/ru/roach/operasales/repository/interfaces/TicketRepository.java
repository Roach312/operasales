package ru.roach.operasales.repository.interfaces;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.roach.operasales.model.ticket.Ticket;
import ru.roach.operasales.repository.entities.TicketEntity;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    TicketEntity getById(Long id);

    @Query(name = "ticket.return")
    @Modifying
    int returnTicket(boolean newStatePattern, Long idTicketPattern);

}
