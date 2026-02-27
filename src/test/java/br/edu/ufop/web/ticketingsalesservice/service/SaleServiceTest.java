package br.edu.ufop.web.ticketingsalesservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.edu.ufop.web.ticketingsalesservice.dto.sale.SaleRequestDTO;
import br.edu.ufop.web.ticketingsalesservice.entity.EventEntity;
import br.edu.ufop.web.ticketingsalesservice.entity.SaleEntity;
import br.edu.ufop.web.ticketingsalesservice.enums.EventType;
import br.edu.ufop.web.ticketingsalesservice.enums.SaleStatus;
import br.edu.ufop.web.ticketingsalesservice.exception.BusinessException;
import br.edu.ufop.web.ticketingsalesservice.repository.SaleRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SaleServiceTest {

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private EventService eventService;

    @InjectMocks
    private SaleService saleService;

    @Test
    void createShouldPersistSaleWhenInsideSalesWindow() {
        UUID eventId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();

        EventEntity event = EventEntity.builder()
                .id(eventId)
                .description("Evento")
                .type(EventType.OUTROS)
                .dateTime(LocalDateTime.now().plusDays(3))
                .startingSales(LocalDateTime.now().minusDays(1))
                .endingSales(LocalDateTime.now().plusDays(2))
                .price(100f)
                .build();

        when(eventService.getEntityById(eventId)).thenReturn(event);

        SaleEntity savedSale = SaleEntity.builder()
                .id(UUID.randomUUID())
                .userId(userId)
                .event(event)
                .status(SaleStatus.EM_ABERTO)
                .dateTime(LocalDateTime.now())
                .build();

        when(saleRepository.save(any(SaleEntity.class))).thenReturn(savedSale);

        SaleRequestDTO request = SaleRequestDTO.builder()
                .eventId(eventId)
                .userId(userId)
                .build();

        var response = saleService.create(request);

        assertEquals(userId, response.getUserId());
        assertEquals(eventId, response.getEvent().getId());
    }

    @Test
    void createShouldThrowBusinessExceptionWhenOutsideSalesWindow() {
        UUID eventId = UUID.randomUUID();

        EventEntity event = EventEntity.builder()
                .id(eventId)
                .description("Evento")
                .type(EventType.PALESTRA)
                .dateTime(LocalDateTime.now().plusDays(5))
                .startingSales(LocalDateTime.now().plusDays(1))
                .endingSales(LocalDateTime.now().plusDays(3))
                .price(40f)
                .build();

        when(eventService.getEntityById(eventId)).thenReturn(event);

        SaleRequestDTO request = SaleRequestDTO.builder()
                .eventId(eventId)
                .userId(UUID.randomUUID())
                .build();

        assertThrows(BusinessException.class, () -> saleService.create(request));
    }
}
