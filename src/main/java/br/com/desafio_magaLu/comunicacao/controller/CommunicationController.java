package br.com.desafio_magaLu.comunicacao.controller;

import br.com.desafio_magaLu.comunicacao.domain.dto.CommunicationDTORequest;
import br.com.desafio_magaLu.comunicacao.domain.dto.CommunicationDTOResponse;
import br.com.desafio_magaLu.comunicacao.service.CommunicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/communication")
public class CommunicationController {

    private final CommunicationService communicationService;

    @Operation(summary = "Realiza um novo agendamento", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Agendamento cadastrado  com sucesso")
    })
    @PostMapping("/new")
    public ResponseEntity<CommunicationDTOResponse> scheduleCommunication(@RequestBody CommunicationDTORequest communicationDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(communicationService.scheduleCommunication(communicationDTO));

    }

    @Operation(summary = "Realiza busca de um  agendamento", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca de agendamento efetuada  com sucesso")
    })
    @GetMapping("/findSchedulle/{schedulleId}")
    public ResponseEntity<CommunicationDTOResponse> findScheduleCommunicationById(@RequestParam Long schedulleId) {
        return ResponseEntity.status(HttpStatus.OK).body(communicationService.findScheduleCommunicationById(schedulleId));

    }

    @Operation(summary = "Realiza o cancelamento do agendamento", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Agendamento cancelado com sucesso!")
    })
    @PatchMapping("/cancel/{schedulleId}")
    public ResponseEntity<CommunicationDTOResponse> cancelScheduleCommunicationById(@RequestParam Long schedulleId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(communicationService.cancelScheduleCommunicationById(schedulleId));

    }

}
