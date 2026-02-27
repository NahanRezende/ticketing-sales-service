package br.edu.ufop.web.ticketingsalesservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.edu.ufop.web.ticketingsalesservice.dto.event.EventRequestDTO;
import br.edu.ufop.web.ticketingsalesservice.dto.event.EventResponseDTO;
import br.edu.ufop.web.ticketingsalesservice.enums.EventType;
import br.edu.ufop.web.ticketingsalesservice.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = EventController.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EventService eventService;

    @Test
    void createShouldReturn201WithPayload() throws Exception {
        EventRequestDTO request = EventRequestDTO.builder()
                .description("Palestra Teste")
                .type(EventType.PALESTRA)
                .dateTime(LocalDateTime.now().plusDays(5))
                .startingSales(LocalDateTime.now().plusDays(1))
                .endingSales(LocalDateTime.now().plusDays(4))
                .price(25f)
                .build();

        EventResponseDTO response = EventResponseDTO.builder()
                .id(UUID.randomUUID())
                .description(request.getDescription())
                .type(request.getType())
                .dateTime(request.getDateTime())
                .startingSales(request.getStartingSales())
                .endingSales(request.getEndingSales())
                .price(request.getPrice())
                .build();

        when(eventService.create(any(EventRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description").value("Palestra Teste"))
                .andExpect(jsonPath("$.type").value("PALESTRA"));
    }
}
