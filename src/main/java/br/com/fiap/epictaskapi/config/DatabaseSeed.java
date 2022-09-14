package br.com.fiap.epictaskapi.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.epictaskapi.model.Task;
import br.com.fiap.epictaskapi.model.User;
import br.com.fiap.epictaskapi.repository.TaskRepository;
import br.com.fiap.epictaskapi.repository.UserRepository;

@Configuration //para ser executada no início
public class DatabaseSeed implements CommandLineRunner {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    //Método implementado da Classe pai
    @Override
    public void run(String... args) throws Exception {

        taskRepository.saveAll(List.of(
            new Task("BD", "Modelar o banco de dados"),
            new Task("Prototipo", "Prototipar as telas"),
            new Task("Bug", "Modelar o banco de dados"),
            new Task("Deploy", "Modelar o banco de dados"),
            new Task("Login", "Modelar o banco de dados"),
            new Task("Log out", "Modelar o banco de dados"),
            new Task("Cadastro de cliente", "Modelar o banco de dados"),
            new Task("Consulta de cliente", "Modelar o banco de dados")

        ));

        userRepository.save(
            new User()
        .name("João")
        .email("joao@gmail.com")
        .password("$2a$12$4AktpRaP8YAFRgxmGtFTr.uz17XveaJDWU9WPoaVIGSMdW7lpCfHa")
    );

    }


    
}
