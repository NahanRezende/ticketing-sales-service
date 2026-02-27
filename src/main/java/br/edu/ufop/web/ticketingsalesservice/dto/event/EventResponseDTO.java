package br.edu.ufop.web.ticketingsalesservice.dto.event;

import br.edu.ufop.web.ticketingsalesservice.enums.EventType;
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
public class EventResponseDTO {

    private UUID id;
    private String description;
    private EventType type;
    private LocalDateTime dateTime;
    private LocalDateTime startingSales;
    private LocalDateTime endingSales;
    private Float price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
