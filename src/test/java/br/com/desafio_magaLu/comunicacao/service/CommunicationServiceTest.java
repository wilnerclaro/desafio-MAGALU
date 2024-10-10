package br.com.desafio_magaLu.comunicacao.service;

import br.com.desafio_magaLu.comunicacao.domain.CommunicationChannel;
import br.com.desafio_magaLu.comunicacao.domain.Communications;
import br.com.desafio_magaLu.comunicacao.domain.Status;
import br.com.desafio_magaLu.comunicacao.domain.dto.CommunicationDTO;
import br.com.desafio_magaLu.comunicacao.exception.CommunicationException;
import br.com.desafio_magaLu.comunicacao.mapper.CommunicationMapper;
import br.com.desafio_magaLu.comunicacao.repository.CommunicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommunicationServiceTest {
    @InjectMocks
    CommunicationService communicationService;
    @Mock
    CommunicationRepository communicationRepository;
    @Mock
    CommunicationMapper communicationMapper;

    CommunicationDTO communicationDTO;
    Communications communications;

    @BeforeEach
    void setUp() {
        communications = new Communications();
        communications.setId(1L);
        communications.setRecipient("TODOS");
        communications.setScheduleTime(LocalDateTime.now());
        communications.setChannel(CommunicationChannel.EMAIL);
        communications.setStatus(Status.SCHEDULED);
        communications.setMenssage("CENARIO 01");
        communicationDTO = new CommunicationDTO(LocalDateTime.now(), "CENARIO 1", "TODOS", CommunicationChannel.EMAIL);
    }

    @Test
    void deveCriarAgendamentoComSucesso() {


        when(communicationRepository.save(any(Communications.class))).thenReturn(communications);
        var scheduleCommunication = communicationRepository.save(communications);

        verify(communicationRepository).save(communications);
        verifyNoMoreInteractions(communicationMapper, communicationRepository);
    }

    @Test
    void deveDarExcptionCasoOcorraAlgumErroDuranteACriacaoDeUmAgendamento() {

        when(communicationMapper.convertToCommunicationEntity(communicationDTO)).thenReturn(communications);
        when(communicationRepository.save(any(Communications.class))).thenThrow(new CommunicationException("Falha ao agendar o envio da requisição: "));

        CommunicationException ex = assertThrows(CommunicationException.class, () -> {
            communicationService.scheduleCommunication(communicationDTO);
        });

        assertEquals("Falha ao agendar o envio da requisição: ", ex.getMessage());
    }
}