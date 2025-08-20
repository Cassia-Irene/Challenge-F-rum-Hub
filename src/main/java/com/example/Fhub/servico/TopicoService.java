package com.example.Fhub.servico;

import com.example.Fhub.modelo.Topico;
import com.example.Fhub.repositorio.TopicoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;

    public TopicoService(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    // Criar tópico com data de criação atual
    public Topico criarTopico(Topico topico) {
        topico.setDataCriacao(LocalDateTime.now());
        return topicoRepository.save(topico);
    }

    // Listar todos os tópicos
    public List<Topico> listarTopicos() {
        return topicoRepository.findAll();
    }

    // Buscar tópico por ID
    public Topico buscarPorId(Long id) {
        return topicoRepository.findById(id).orElse(null);
    }

    // Verifica se tópico existe por ID
    public boolean existePorId(Long id) {
        return topicoRepository.existsById(id);
    }

    // Atualizar tópico
    public Topico atualizarTopico(Long id, Topico topicoAtualizado) {
        Topico topico = topicoRepository.findById(id).orElse(null);
        if (topico == null) {
            return null;
        }
        topico.setTitulo(topicoAtualizado.getTitulo());
        topico.setMensagem(topicoAtualizado.getMensagem());
        topico.setCurso(topicoAtualizado.getCurso());
        topico.setStatus(topicoAtualizado.getStatus());
        return topicoRepository.save(topico);
    }

    // Deletar tópico, retorna true se deletou, false se não existe
    public boolean deletarTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            return false;
        }
        topicoRepository.deleteById(id);
        return true;
    }

    // Verificar duplicidade por título e mensagem
    public boolean existeTopico(String titulo, String mensagem) {
        return topicoRepository.findByTituloAndMensagem(titulo, mensagem).isPresent();
    }
}