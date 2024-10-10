package br.com.desafio_magaLu.comunicacao.mapper;

import br.com.desafio_magaLu.comunicacao.domain.Communications;
import br.com.desafio_magaLu.comunicacao.domain.Status;
import br.com.desafio_magaLu.comunicacao.domain.dto.CommunicationDTO;
import org.springframework.stereotype.Component;

@Component
public class CommunicationMapper {

    public Communications convertToCommunicationEntity(CommunicationDTO communicationDTO) {
        return Communications.builder()
                .channel(communicationDTO.channel())
                .status(Status.SCHEDULED)
                .scheduleTime(communicationDTO.scheduleTime())
                .recipient(communicationDTO.recipient().toUpperCase())
                .menssage(communicationDTO.menssage())
                .build();
    }

    public CommunicationDTO convertToCommunicationDTO(Communications communications) {
        return CommunicationDTO.builder()
                .channel(communications.getChannel())
                .recipient(communications.getRecipient().toUpperCase())
                .menssage(communications.getMenssage())
                .scheduleTime(communications.getScheduleTime())
                .build();
    }
}
