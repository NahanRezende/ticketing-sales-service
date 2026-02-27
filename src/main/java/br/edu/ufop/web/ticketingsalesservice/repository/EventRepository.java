package br.edu.ufop.web.ticketingsalesservice.repository;

import br.edu.ufop.web.ticketingsalesservice.entity.EventEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, UUID> {
}
