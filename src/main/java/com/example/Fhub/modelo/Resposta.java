package com.example.Fhub.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String mensagem;

    private LocalDateTime dataCriacao;

    @ManyToOne
    private Usuario autor;

    @ManyToOne
    private Topico topico;
}
