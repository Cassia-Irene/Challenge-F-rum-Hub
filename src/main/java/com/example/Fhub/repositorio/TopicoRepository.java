package com.example.Fhub.repositorio;

import com.example.Fhub.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    // Método para verificar se já existe um tópico com mesmo título e mensagem
    Optional<Topico> findByTituloAndMensagem(String titulo, String mensagem);
}
