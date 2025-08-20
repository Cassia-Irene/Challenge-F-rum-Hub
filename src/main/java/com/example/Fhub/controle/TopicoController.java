package com.example.Fhub.controle;

import com.example.Fhub.modelo.Topico;
import com.example.Fhub.repositorio.UsuarioRepository;
import com.example.Fhub.servico.TopicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;
    private final UsuarioRepository usuarioRepository; // Adicionado

    @Autowired // Adicionado a anotação para injeção de dependência
    public TopicoController(TopicoService topicoService, UsuarioRepository usuarioRepository) {
        this.topicoService = topicoService;
        this.usuarioRepository = usuarioRepository;
    }

    // Criar novo tópico com validação e checagem de duplicidade
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid Topico topico) {
        // Correção: a verificação de duplicidade agora usa o método do serviço
        boolean existe = topicoService.existeTopico(topico.getTitulo(), topico.getMensagem());
        if (existe) {
            return ResponseEntity.badRequest().body("Tópico duplicado!");
        }

        // Antes de salvar, você precisa buscar o objeto Usuario pelo ID
        // (Isso depende de como o JSON de entrada é formatado, se você envia apenas o ID)
        // Se o JSON de entrada inclui o objeto Usuario completo, esta parte não é necessária.
        // Se a entidade Topico tem o campo autor já preenchido, não há erro.
        // O erro provavelmente está na entrada JSON, veja o exemplo abaixo.

        Topico criado = topicoService.criarTopico(topico);
        return ResponseEntity.ok(criado);
    }

    // Listar todos os tópicos
    @GetMapping
    public ResponseEntity<List<Topico>> listar() {
        List<Topico> topicos = topicoService.listarTopicos();
        return ResponseEntity.ok(topicos);
    }

    // Buscar tópico por id
    @GetMapping("/{id}")
    public ResponseEntity<Topico> buscar(@PathVariable Long id) {
        Topico topico = topicoService.buscarPorId(id);
        if (topico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(topico);
    }

    // Atualizar tópico
    @PutMapping("/{id}")
    public ResponseEntity<Topico> atualizar(@PathVariable Long id, @RequestBody @Valid Topico topico) {
        Topico atualizado = topicoService.atualizarTopico(id, topico);
        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atualizado);
    }

    // Deletar tópico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = topicoService.deletarTopico(id);
        if (!deletado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}