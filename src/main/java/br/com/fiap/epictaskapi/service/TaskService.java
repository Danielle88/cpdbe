package br.com.fiap.epictaskapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.repository.TaskRepository;

@Service // (Quero injetar essa classe no Controller)
public class TaskService {

    @Autowired // (estou injetando um repository aqui)
    private TaskRepository repository;

    public Page<Task> listAll(Pageable pageble) { //antes devolvia um List de task (tds ao msm tempo), agora ele devolve uma Pagina de tasks
        return repository.findAll(pageble);
    }

    public void create(Task task){
        repository.save(task);
    }
    
    //Optional pq eu não sei se vai existir o objeto com esse id ou não. Então para não ter que lançar excessão, criaram esse Optional<>
    public Optional<Task> getById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}
