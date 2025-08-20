package com.example.Fhub.controle;

import com.example.Fhub.modelo.Topico;
import com.example.Fhub.servico.TopicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    // Criar novo tópico com validação e checagem de duplicidade
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid Topico topico) {
        boolean existe = topicoService.existeTopico(topico.getTitulo(), topico.getMensagem());
        if (existe) {
            return ResponseEntity.badRequest().body("Tópico duplicado!");
        }

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
            return ResponseEntity.notFound().build(); // 404 se não existir
        }
        return ResponseEntity.ok(topico); // 200 OK se encontrado
    }

    // Atualizar tópico
    @PutMapping("/{id}")
    public ResponseEntity<Topico> atualizar(@PathVariable Long id, @RequestBody @Valid Topico topico) {
        Topico atualizado = topicoService.atualizarTopico(id, topico);
        if (atualizado == null) {
            return ResponseEntity.notFound().build(); // 404 se não existir
        }
        return ResponseEntity.ok(atualizado); // 200 OK com objeto atualizado
    }

    // Deletar tópico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = topicoService.deletarTopico(id);
        if (!deletado) {
            return ResponseEntity.notFound().build(); // 404 se não existir
        }
        return ResponseEntity.noContent().build(); // 204 No Content se deletado
    }
}
