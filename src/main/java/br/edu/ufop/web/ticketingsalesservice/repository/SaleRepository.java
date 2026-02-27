package br.edu.ufop.web.ticketingsalesservice.repository;

import br.edu.ufop.web.ticketingsalesservice.entity.SaleEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<SaleEntity, UUID> {
}
