package br.com.desafio_magaLu.comunicacao.controller;

import br.com.desafio_magaLu.comunicacao.domain.dto.CommunicationDTO;
import br.com.desafio_magaLu.comunicacao.service.CommunicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/communication")
public class CommunicationController {

    private final CommunicationService communicationService;

    @Operation(summary = "Realiza um novo agendamento", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Agendamento cadastrado  com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao  realizar agendamento")
    })
    @PostMapping("/new")
    public ResponseEntity<CommunicationDTO> scheduleCommunication(@RequestBody CommunicationDTO communicationDTO) {
        CommunicationDTO communication = communicationService.scheduleCommunication(communicationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(communication);

    }

}
