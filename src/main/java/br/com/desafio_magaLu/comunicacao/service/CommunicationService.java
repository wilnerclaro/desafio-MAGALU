package br.com.desafio_magaLu.comunicacao.service;

import br.com.desafio_magaLu.comunicacao.domain.Communications;
import br.com.desafio_magaLu.comunicacao.domain.Status;
import br.com.desafio_magaLu.comunicacao.domain.dto.CommunicationDTORequest;
import br.com.desafio_magaLu.comunicacao.domain.dto.CommunicationDTOResponse;
import br.com.desafio_magaLu.comunicacao.exception.CommunicationException;
import br.com.desafio_magaLu.comunicacao.mapper.CommunicationMapper;
import br.com.desafio_magaLu.comunicacao.repository.CommunicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommunicationService {

    private final CommunicationRepository communicationRepository;
    private final CommunicationMapper communicationMapper;

    public CommunicationDTOResponse scheduleCommunication(CommunicationDTORequest communicationDTO) {
        try {
            Communications communicationToEntity = communicationMapper.convertToCommunicationEntity(communicationDTO);
            return communicationMapper.convertToCommunicationDTO(communicationRepository.save(communicationToEntity));
        } catch (Exception e) {
            throw new CommunicationException("Falha ao agendar o envio da requisição: ");
        }
    }

    public CommunicationDTOResponse findScheduleCommunicationById(Long schedulleId) {
        Communications communication = communicationRepository.findById(schedulleId).orElseThrow(() -> new CommunicationException("Não foi encontrado o agendamento informado"));
        return communicationMapper.convertToCommunicationDTO(communication);

    }

    public CommunicationDTOResponse cancelScheduleCommunicationById(Long schedulleId) {
        Communications communication = communicationRepository.findById(schedulleId).orElseThrow(() -> new CommunicationException("Não foi encontrado o agendamento informado"));
        communication.setStatus(Status.CANCELLED);
        communication.setUpdateTime(LocalDateTime.now());
        communicationRepository.save(communication);
        return communicationMapper.convertToCommunicationDTO(communication);

    }
}
