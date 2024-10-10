package br.com.desafio_magaLu.comunicacao.service;

import br.com.desafio_magaLu.comunicacao.domain.Communications;
import br.com.desafio_magaLu.comunicacao.domain.dto.CommunicationDTO;
import br.com.desafio_magaLu.comunicacao.exception.CommunicationException;
import br.com.desafio_magaLu.comunicacao.mapper.CommunicationMapper;
import br.com.desafio_magaLu.comunicacao.repository.CommunicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunicationService {

    private final CommunicationRepository communicationRepository;
    private final CommunicationMapper communicationMapper;

    public CommunicationDTO scheduleCommunication(CommunicationDTO communicationDTO) {
        try {
            Communications communicationToEntity = communicationMapper.convertToCommunicationEntity(communicationDTO);
            return communicationMapper.convertToCommunicationDTO(communicationRepository.save(communicationToEntity));
        } catch (Exception e) {
            throw new CommunicationException("Falha ao agendar o envio da requisição: " + e.getMessage());
        }
    }
}
