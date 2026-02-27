package br.edu.ufop.web.ticketingsalesservice.service;

import br.edu.ufop.web.ticketingsalesservice.dto.event.EventRequestDTO;
import br.edu.ufop.web.ticketingsalesservice.dto.event.EventResponseDTO;
import br.edu.ufop.web.ticketingsalesservice.entity.EventEntity;
import br.edu.ufop.web.ticketingsalesservice.exception.BusinessException;
import br.edu.ufop.web.ticketingsalesservice.exception.NotFoundException;
import br.edu.ufop.web.ticketingsalesservice.mapper.EventMapper;
import br.edu.ufop.web.ticketingsalesservice.repository.EventRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<EventResponseDTO> findAll() {
        return eventRepository.findAll().stream()
                .map(EventMapper::toResponse)
                .toList();
    }

    public EventResponseDTO findById(UUID id) {
        EventEntity event = getEntityById(id);
        return EventMapper.toResponse(event);
    }

    @Transactional
    public EventResponseDTO create(EventRequestDTO request) {
        validateDates(request);
        EventEntity event = EventMapper.toEntity(request);
        event = eventRepository.save(event);
        return EventMapper.toResponse(event);
    }

    @Transactional
    public EventResponseDTO update(UUID id, EventRequestDTO request) {
        validateDates(request);
        EventEntity event = getEntityById(id);
        event.setDescription(request.getDescription());
        event.setType(request.getType());
        event.setDateTime(request.getDateTime());
        event.setStartingSales(request.getStartingSales());
        event.setEndingSales(request.getEndingSales());
        event.setPrice(request.getPrice());
        event = eventRepository.save(event);
        return EventMapper.toResponse(event);
    }

    @Transactional
    public void delete(UUID id) {
        EventEntity event = getEntityById(id);
        eventRepository.delete(event);
    }

    public EventEntity getEntityById(UUID id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event not found for id: " + id));
    }

    private void validateDates(EventRequestDTO request) {
        if (request.getStartingSales().isAfter(request.getEndingSales())) {
            throw new BusinessException("startingSales must be before endingSales.");
        }
        if (request.getEndingSales().isAfter(request.getDateTime())) {
            throw new BusinessException("endingSales must be before or equal to event dateTime.");
        }
    }
}
