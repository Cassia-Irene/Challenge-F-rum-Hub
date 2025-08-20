package com.example.Fhub.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @NotBlank
    @Column(length = 2000)
    private String mensagem;

    @ManyToOne // Relação com a entidade Usuario
    private Usuario autor;

    @NotBlank
    private String curso;

    private LocalDateTime dataCriacao;

    private String status;
}