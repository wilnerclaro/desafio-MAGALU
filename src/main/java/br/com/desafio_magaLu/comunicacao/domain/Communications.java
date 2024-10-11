package br.com.desafio_magaLu.comunicacao.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_COMUNICACAO_SCHEDULE")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Communications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AGENDAMENTO")
    private Long id;
    @Column(name = "DT_AGENDAMENTO")
    private LocalDateTime scheduleTime;
    @Column(name = "DT_ATUALIZACAO")
    private LocalDateTime updateTime;
    @Column(name = "MENSSAGEM")
    private String menssage;
    @Column(name = "DESTINATARIO")
    private String recipient;
    @Column(name = "TIPO_ENVIO")
    @Enumerated(EnumType.STRING)
    private CommunicationChannel channel;
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "MENSSAGEM_ID")
    private String rabbitMessageId;


}
