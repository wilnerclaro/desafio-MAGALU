package br.com.desafio_magaLu.comunicacao.domain.dto;

import br.com.desafio_magaLu.comunicacao.domain.CommunicationChannel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommunicationDTO(
        LocalDateTime scheduleTime,
        String menssage, String recipient,
        CommunicationChannel channel) {
}
