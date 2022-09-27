package br.com.fiap.epictaskapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.fiap.epictaskapi.dto.UserDto;

@Entity
@Table(name = "TB_USER")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Email
    private String email;
    @Size(min = 8)
    @JsonProperty(access = Access.WRITE_ONLY) //usuário só escreve, não lê a senha
    private String password;

    public User name(String name){
        Assert.notNull(name, "name is required");
        this.name= name;
        return this;
    }

    public User password(String password){
        Assert.notNull(password, "password is required");
        this.password= password;
        return this;
    }

    public User email(String email){
        Assert.notNull(email, "email is required");
        this.email= email;
        return this;
    }

    public UserDto toDto(){
        return new UserDto(id, name, email);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
    }

    
    
}
