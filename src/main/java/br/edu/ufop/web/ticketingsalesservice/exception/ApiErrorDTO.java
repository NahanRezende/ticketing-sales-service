package br.edu.ufop.web.ticketingsalesservice.exception;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiErrorDTO {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
}
