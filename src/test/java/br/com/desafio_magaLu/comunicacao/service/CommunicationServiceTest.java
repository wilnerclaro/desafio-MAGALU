package br.com.desafio_magaLu.comunicacao.service;

import br.com.desafio_magaLu.comunicacao.domain.CommunicationChannel;
import br.com.desafio_magaLu.comunicacao.domain.Communications;
import br.com.desafio_magaLu.comunicacao.domain.Status;
import br.com.desafio_magaLu.comunicacao.domain.dto.CommunicationDTORequest;
import br.com.desafio_magaLu.comunicacao.domain.dto.CommunicationDTOResponse;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommunicationServiceTest {
    @InjectMocks
    CommunicationService communicationService;
    @Mock
    CommunicationRepository communicationRepository;
    @Mock
    CommunicationMapper communicationMapper;

    CommunicationDTOResponse communicationDTOResponse;
    CommunicationDTORequest communicationDTORequest;
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

        communicationDTOResponse = new CommunicationDTOResponse(
                communications.getScheduleTime(),
                communications.getMenssage(),
                communications.getRecipient(),
                communications.getChannel(),
                communications.getStatus()
        );
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

        when(communicationMapper.convertToCommunicationEntity(communicationDTORequest)).thenReturn(communications);
        when(communicationRepository.save(any(Communications.class))).thenThrow(new CommunicationException("Falha ao agendar o envio da requisição: "));

        CommunicationException ex = assertThrows(CommunicationException.class, () -> {
            communicationService.scheduleCommunication(communicationDTORequest);
        });

        assertEquals("Falha ao agendar o envio da requisição: ", ex.getMessage());
    }

    @Test
    void deveEncontrarAgendamentoPorIdComSucesso() {
        Long scheduleId = communications.getId();
        when(communicationRepository.findById(scheduleId)).thenReturn(Optional.of(communications));
        when(communicationMapper.convertToCommunicationDTO(communications)).thenReturn(communicationDTOResponse);

        CommunicationDTOResponse scheduleCommunicationById = communicationService.findScheduleCommunicationById(scheduleId);

        assertNotNull(scheduleCommunicationById);
        assertEquals(communications.getMenssage(), scheduleCommunicationById.menssage());
        assertEquals(communications.getRecipient(), scheduleCommunicationById.recipient());
        assertEquals(communications.getChannel(), scheduleCommunicationById.channel());
        assertEquals(communications.getStatus(), scheduleCommunicationById.status());

        verify(communicationRepository, times(1)).findById(scheduleId);
        verify(communicationMapper, times(1)).convertToCommunicationDTO(communications);
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrarAgendamento() {
        Long scheduleId = 2L;
        when(communicationRepository.findById(scheduleId)).thenReturn(Optional.empty());
        CommunicationException exception = assertThrows(CommunicationException.class, () ->
                communicationService.findScheduleCommunicationById(scheduleId)
        );
        assertEquals("Não foi encontrado o agendamento informado", exception.getMessage());

        verify(communicationRepository, times(1)).findById(scheduleId);
        verifyNoMoreInteractions(communicationRepository);
        verifyNoInteractions(communicationMapper);
    }

    @Test
    void deveCancelarAgendamentoPorIdComSucesso() {

        Long scheduleId = communications.getId();

        when(communicationRepository.findById(scheduleId)).thenReturn(Optional.of(communications));
        when(communicationRepository.save(any(Communications.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(communicationMapper.convertToCommunicationDTO(any(Communications.class))).thenAnswer(invocation -> {
            Communications updatedCommunication = invocation.getArgument(0);
            updatedCommunication.setStatus(Status.CANCELLED);
            updatedCommunication.setUpdateTime(LocalDateTime.now());
            return new CommunicationDTOResponse(
                    updatedCommunication.getScheduleTime(),
                    updatedCommunication.getMenssage(),
                    updatedCommunication.getRecipient(),
                    updatedCommunication.getChannel(),
                    updatedCommunication.getStatus()
            );
        });

        CommunicationDTOResponse cancelScheduleCommunication = communicationService.cancelScheduleCommunicationById(communications.getId());

        assertNotNull(cancelScheduleCommunication);
        assertEquals(Status.CANCELLED, communications.getStatus());
        assertEquals(communications.getMenssage(), cancelScheduleCommunication.menssage());
        assertEquals(communications.getRecipient(), cancelScheduleCommunication.recipient());
        assertEquals(communications.getChannel(), cancelScheduleCommunication.channel());
        assertEquals(communications.getStatus(), cancelScheduleCommunication.status());

        verify(communicationRepository, times(1)).findById(scheduleId);
        verify(communicationRepository, times(1)).save(communications);
        verify(communicationMapper, times(1)).convertToCommunicationDTO(communications);
    }

    @Test
    void deveLancarExcecaoAoCancelarAgendamentoNaoEncontrado() {
        Long scheduleId = 2L;
        when(communicationRepository.findById(scheduleId)).thenReturn(Optional.empty());

        CommunicationException exception = assertThrows(CommunicationException.class, () ->
                communicationService.cancelScheduleCommunicationById(scheduleId)
        );
        assertEquals("Não foi encontrado o agendamento informado", exception.getMessage());

        verify(communicationRepository, times(1)).findById(scheduleId);
        verifyNoMoreInteractions(communicationRepository);
        verifyNoInteractions(communicationMapper);
    }
}