package br.edu.ufop.web.ticketingsalesservice.mapper;

import br.edu.ufop.web.ticketingsalesservice.dto.sale.SaleResponseDTO;
import br.edu.ufop.web.ticketingsalesservice.entity.SaleEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SaleMapper {

    public static SaleResponseDTO toResponse(SaleEntity entity) {
        return SaleResponseDTO.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .event(EventMapper.toResponse(entity.getEvent()))
                .dateTime(entity.getDateTime())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
