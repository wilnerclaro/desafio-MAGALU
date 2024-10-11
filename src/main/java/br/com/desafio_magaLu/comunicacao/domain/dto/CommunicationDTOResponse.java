package br.com.desafio_magaLu.comunicacao.domain.dto;

import br.com.desafio_magaLu.comunicacao.domain.CommunicationChannel;
import br.com.desafio_magaLu.comunicacao.domain.Status;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CommunicationDTOResponse(
        LocalDateTime scheduleTime,
        String menssage, String recipient,
        CommunicationChannel channel,
        Status status) {
}
