package br.com.fiap.epictaskapi.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.httpBasic()
            .and()
            .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/api/task/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/task/").authenticated()
                .anyRequest().denyAll()
            .and()
                .csrf().disable() //api não guarda a sessão (login do usuario), por isso nao preciso de chave token(que o csrf obriga) para validar que o usuario é ele mesmo.
        ;

        return http.build();

    }

    // @Bean
    // public UserDetailsService users(){
    //     UserDetails user = User.builder()
    //         .username("joao@gmail.com")
    //         .password("$2a$12$4AktpRaP8YAFRgxmGtFTr.uz17XveaJDWU9WPoaVIGSMdW7lpCfHa")
    //         .roles("USER")
    //         .build();
    //     return new InMemoryUserDetailsManager(user);

    // }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
}
