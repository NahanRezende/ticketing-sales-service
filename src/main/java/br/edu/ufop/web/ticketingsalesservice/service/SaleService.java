package br.edu.ufop.web.ticketingsalesservice.service;

import br.edu.ufop.web.ticketingsalesservice.dto.sale.SaleRequestDTO;
import br.edu.ufop.web.ticketingsalesservice.dto.sale.SaleResponseDTO;
import br.edu.ufop.web.ticketingsalesservice.entity.EventEntity;
import br.edu.ufop.web.ticketingsalesservice.entity.SaleEntity;
import br.edu.ufop.web.ticketingsalesservice.enums.SaleStatus;
import br.edu.ufop.web.ticketingsalesservice.exception.BusinessException;
import br.edu.ufop.web.ticketingsalesservice.exception.NotFoundException;
import br.edu.ufop.web.ticketingsalesservice.mapper.SaleMapper;
import br.edu.ufop.web.ticketingsalesservice.repository.SaleRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final EventService eventService;

    public List<SaleResponseDTO> findAll() {
        return saleRepository.findAll().stream()
                .map(SaleMapper::toResponse)
                .toList();
    }

    public SaleResponseDTO findById(UUID id) {
        SaleEntity sale = getEntityById(id);
        return SaleMapper.toResponse(sale);
    }

    @Transactional
    public SaleResponseDTO create(SaleRequestDTO request) {
        EventEntity event = eventService.getEntityById(request.getEventId());
        validateSalesWindow(event);

        SaleEntity sale = SaleEntity.builder()
                .userId(request.getUserId())
                .event(event)
                .status(request.getStatus())
                .build();

        sale = saleRepository.save(sale);
        return SaleMapper.toResponse(sale);
    }

    @Transactional
    public SaleResponseDTO update(UUID id, SaleRequestDTO request) {
        SaleEntity sale = getEntityById(id);
        EventEntity event = eventService.getEntityById(request.getEventId());

        sale.setUserId(request.getUserId());
        sale.setEvent(event);
        if (request.getStatus() != null) {
            sale.setStatus(request.getStatus());
        }
        sale = saleRepository.save(sale);
        return SaleMapper.toResponse(sale);
    }

    @Transactional
    public void delete(UUID id) {
        SaleEntity sale = getEntityById(id);
        saleRepository.delete(sale);
    }

    private SaleEntity getEntityById(UUID id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Sale not found for id: " + id));
    }

    private void validateSalesWindow(EventEntity event) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(event.getStartingSales()) || now.isAfter(event.getEndingSales())) {
            throw new BusinessException("Sale is outside the event sales period.");
        }
    }
}
