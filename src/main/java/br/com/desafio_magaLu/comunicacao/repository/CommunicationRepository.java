package br.com.desafio_magaLu.comunicacao.repository;

import br.com.desafio_magaLu.comunicacao.domain.Communications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunicationRepository extends JpaRepository<Communications, Long> {
}
