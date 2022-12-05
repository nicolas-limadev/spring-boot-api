package com.exemple.demo.tarefas.facade;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.exemple.demo.tarefas.Tarefa;
import com.exemple.demo.tarefas.TarefaRepository;
import com.exemple.demo.tarefas.dto.TarefaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarefasFacade {

    @Autowired
    private TarefaRepository repository;
    
    private static final Map<Long, TarefaDTO> tarefas = new HashMap<>();

    public TarefaDTO criar(TarefaDTO tarefaDTO){
        Tarefa tarefa = new Tarefa();

        tarefa.setDescricao(tarefa.getDescricao());
        tarefa.setTitulo(tarefa.getTitulo());
        repository.save(tarefa);
        tarefaDTO.setId(tarefa.getId());

        return tarefaDTO;
    }

    public TarefaDTO atualizar (TarefaDTO tarefaDTO, long tarefaId){
        Tarefa tarefaDatabase = repository.getOne(tarefaId);
        tarefaDatabase.setDescricao(tarefaDTO.getDescricao());
        tarefaDatabase.setTitulo(tarefaDTO.getTitulo());

        return tarefaDTO;
    }

    private TarefaDTO converter (Tarefa tarefa){

        TarefaDTO result = new TarefaDTO();
        result.setId(tarefa.getId());
        result.setDescricao(tarefa.getDescricao());
        result.setTitulo(tarefa.getTitulo());
        return result;

    }

    public List<TarefaDTO> getAll () {
        return repository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }

    public String delete (Long tarefaId) {
        repository.deleteById(tarefaId);
        return "DELETED";
    }
}
