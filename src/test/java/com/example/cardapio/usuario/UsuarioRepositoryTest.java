package com.example.cardapio.usuario;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class UsuarioRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UsuarioRepository repository;

    @Test
    @DisplayName("Exibe se o usuario estiver no banco")
    void findUsuarioByUsuarioAndSenhaCase1(){
        UsuarioRequestDTO usuario = new UsuarioRequestDTO("igor", "123456");
        this.createUser(usuario);

        Optional<Usuario> findUser = repository.findUsuarioByUsuarioAndSenha("igor", "123456");
        assertThat(findUser.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Exibe se o usuario não estiver no banco")
    void findUsuarioByUsuarioAndSenhaCase2(){
        Optional<Usuario> findUser = repository.findUsuarioByUsuarioAndSenha("igor", "1234567");
        assertThat(findUser.isEmpty()).isTrue();
    }

    private void createUser(UsuarioRequestDTO data){
        Usuario user = new Usuario(data);
        this.entityManager.persist(user);
    }
}