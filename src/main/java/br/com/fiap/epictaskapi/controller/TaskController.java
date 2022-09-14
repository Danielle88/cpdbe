package br.com.fiap.epictaskapi.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.service.TaskService;

@RestController
@RequestMapping("/api/task") //todas as requisicoes que forem /api/task cairão nesse controller
public class TaskController {

    @Autowired //(Estou injetando o service aqui)
    private TaskService service;
    
    @GetMapping
    @Cacheable("task") //guarda a informação em cache para mais rápido acesso
    public Page<Task> index(@PageableDefault(size=10, sort="title") Pageable pageable){

        return service.listAll(pageable);

    }

    @PostMapping 
    @CacheEvict(value="task", allEntries = true) //Se eu fizer um post, não posso mais usar info em memória(cache)
    public ResponseEntity<Task> create(@RequestBody @Valid Task task){ //mandando o task pelo body e antes to Validando os campos
        service.create(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @GetMapping("{id}")   //mandando o id pelo path da requisição
    @Cacheable("task") //guarda a informação em cache para mais rápido acesso
    public ResponseEntity<Task> getById(@PathVariable Long id){
        /*Optional<Task> task = service.get(id);

        if(task.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(task.get()); */

        return ResponseEntity.of(service.getById(id)); //esse método 'of' já valida se o objeto está vazio ou não (faz o código acima)
    }

    @DeleteMapping("{id}")
    @CacheEvict(value="task", allEntries = true) //Se eu fizer um delete, não posso mais usar info em memória(cache)
    public ResponseEntity<Task> deletar(@PathVariable Long id){

        Optional<Task> optional = service.getById(id);
        
        if(optional.isEmpty()){  //Se o objeto não existir retornar status not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        //Caso existir, deletar
        service.delete(id);  //esse http status é da familia do 200, ou seja, deu certo, mas nós não retornamos nada pro usuário(no content)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PutMapping("{id}")
    @CacheEvict(value="task", allEntries = true) //Se eu fizer um put, não posso mais usar info em memória(cache)
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody @Valid Task newTask){

        //buscar a tarefa no banco
        Optional<Task> optional = service.getById(id);
        
        if(optional.isEmpty()){  //Se o objeto não existir retornar status not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //se existir, trocar os valores
        Task task = optional.get();
        BeanUtils.copyProperties(newTask, task); //seta todos os atributos mas tira o id (seta null)
        task.setId(id); //setando o mesmo id novamente

        /*
        task.setTitle(newTask.getTitle());
        task.setDescription(newTask.getDescription());
        task.setScore(newTask.getScore());
        task.setStatus(newTask.getStatus()); */

        service.create(task);
        return ResponseEntity.ok(task);

    }

}
