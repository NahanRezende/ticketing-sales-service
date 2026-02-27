package br.edu.ufop.web.ticketingsalesservice.entity;

import br.edu.ufop.web.ticketingsalesservice.enums.SaleStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_sales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID userId;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity event;

    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private SaleStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void beforeCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.dateTime = now;
        this.createdAt = now;
        this.updatedAt = now;
        if (this.status == null) {
            this.status = SaleStatus.EM_ABERTO;
        }
    }

    @PreUpdate
    public void beforeUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
