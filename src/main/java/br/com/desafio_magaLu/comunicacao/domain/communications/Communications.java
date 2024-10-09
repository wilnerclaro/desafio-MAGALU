package br.com.desafio_magaLu.comunicacao.domain.communications;

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
    private Long id;
    private LocalDateTime scheduleTime;
    private String menssage;
    private CommunicationChannel channel;
    private Status status;

}
