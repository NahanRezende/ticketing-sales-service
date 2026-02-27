package br.edu.ufop.web.ticketingsalesservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.edu.ufop.web.ticketingsalesservice.dto.event.EventRequestDTO;
import br.edu.ufop.web.ticketingsalesservice.entity.EventEntity;
import br.edu.ufop.web.ticketingsalesservice.enums.EventType;
import br.edu.ufop.web.ticketingsalesservice.exception.BusinessException;
import br.edu.ufop.web.ticketingsalesservice.repository.EventRepository;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    void createShouldPersistEventWhenDatesAreValid() {
        EventRequestDTO request = EventRequestDTO.builder()
                .description("Show de Rock")
                .type(EventType.SHOW)
                .dateTime(LocalDateTime.now().plusDays(10))
                .startingSales(LocalDateTime.now().plusDays(1))
                .endingSales(LocalDateTime.now().plusDays(9))
                .price(99.9f)
                .build();

        UUID eventId = UUID.randomUUID();
        EventEntity saved = EventEntity.builder()
                .id(eventId)
                .description(request.getDescription())
                .type(request.getType())
                .dateTime(request.getDateTime())
                .startingSales(request.getStartingSales())
                .endingSales(request.getEndingSales())
                .price(request.getPrice())
                .build();

        when(eventRepository.save(any(EventEntity.class))).thenReturn(saved);

        var response = eventService.create(request);

        assertEquals(eventId, response.getId());
        assertEquals("Show de Rock", response.getDescription());
        verify(eventRepository).save(any(EventEntity.class));
    }

    @Test
    void createShouldThrowBusinessExceptionWhenSalesWindowIsInvalid() {
        EventRequestDTO request = EventRequestDTO.builder()
                .description("Evento X")
                .type(EventType.CURSO)
                .dateTime(LocalDateTime.now().plusDays(10))
                .startingSales(LocalDateTime.now().plusDays(8))
                .endingSales(LocalDateTime.now().plusDays(2))
                .price(50f)
                .build();

        assertThrows(BusinessException.class, () -> eventService.create(request));
    }
}
