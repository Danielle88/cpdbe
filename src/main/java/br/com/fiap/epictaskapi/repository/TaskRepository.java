package br.com.fiap.epictaskapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.epictaskapi.model.Task;

//Aqui seria a classe com o crud.
//Então criamos uma interface que herda de JpaRepository(que ja tem todos os métodos).
//Mas a JpaRepository é uma interface genérica, ou seja,
//                  aqui temos que colocar o tipo de objeto e o tipo da chave primária(id).
public interface TaskRepository extends JpaRepository<Task, Long>{

    
}
