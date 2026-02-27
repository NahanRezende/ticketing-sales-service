package br.edu.ufop.web.ticketingsalesservice.mapper;

import br.edu.ufop.web.ticketingsalesservice.dto.event.EventRequestDTO;
import br.edu.ufop.web.ticketingsalesservice.dto.event.EventResponseDTO;
import br.edu.ufop.web.ticketingsalesservice.entity.EventEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EventMapper {

    public static EventEntity toEntity(EventRequestDTO dto) {
        return EventEntity.builder()
                .description(dto.getDescription())
                .type(dto.getType())
                .dateTime(dto.getDateTime())
                .startingSales(dto.getStartingSales())
                .endingSales(dto.getEndingSales())
                .price(dto.getPrice())
                .build();
    }

    public static EventResponseDTO toResponse(EventEntity entity) {
        return EventResponseDTO.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .type(entity.getType())
                .dateTime(entity.getDateTime())
                .startingSales(entity.getStartingSales())
                .endingSales(entity.getEndingSales())
                .price(entity.getPrice())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
