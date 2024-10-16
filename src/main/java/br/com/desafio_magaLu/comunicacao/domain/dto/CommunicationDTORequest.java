package br.com.desafio_magaLu.comunicacao.domain.dto;

import br.com.desafio_magaLu.comunicacao.domain.CommunicationChannel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommunicationDTORequest(
        LocalDateTime scheduleTime,
        String message, String recipient,
        CommunicationChannel channel) {
}
