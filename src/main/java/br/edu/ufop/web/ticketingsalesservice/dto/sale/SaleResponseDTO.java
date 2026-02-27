package br.edu.ufop.web.ticketingsalesservice.dto.sale;

import br.edu.ufop.web.ticketingsalesservice.dto.event.EventResponseDTO;
import br.edu.ufop.web.ticketingsalesservice.enums.SaleStatus;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleResponseDTO {

    private UUID id;
    private UUID userId;
    private EventResponseDTO event;
    private LocalDateTime dateTime;
    private SaleStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
