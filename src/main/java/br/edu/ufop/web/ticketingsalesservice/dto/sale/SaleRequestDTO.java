package br.edu.ufop.web.ticketingsalesservice.dto.sale;

import br.edu.ufop.web.ticketingsalesservice.enums.SaleStatus;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleRequestDTO {

    @NotNull
    private UUID userId;

    @NotNull
    private UUID eventId;

    private SaleStatus status;
}
