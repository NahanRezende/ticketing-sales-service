package br.edu.ufop.web.ticketingsalesservice.dto.event;

import br.edu.ufop.web.ticketingsalesservice.enums.EventType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRequestDTO {

    @NotBlank
    private String description;

    @NotNull
    private EventType type;

    @NotNull
    @Future
    private LocalDateTime dateTime;

    @NotNull
    private LocalDateTime startingSales;

    @NotNull
    private LocalDateTime endingSales;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private Float price;
}
