package com.example.cardapio.controllers;

import com.example.cardapio.usuario.Usuario;
import com.example.cardapio.usuario.UsuarioRepository;
import com.example.cardapio.usuario.UsuarioRequestDTO;
import com.example.cardapio.usuario.UsuarioResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Controller("/")
public class UsuarioController {

    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody UsuarioRequestDTO data) {
        try {
            Usuario login = new Usuario(data);
            repository.save(login);
            return new ResponseEntity<>("Usuario criado com sucesso!", HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Falha ao criar o usuario!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<UsuarioResponseDTO> getAll() {
        return repository.findAll().stream().map(UsuarioResponseDTO::new).toList();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            Optional<Usuario> usuario = repository.findById(id);

            if (usuario.isEmpty()) {
                return new ResponseEntity<>("Não foi localizado usuario com o id: " + id, HttpStatus.NOT_FOUND);
            }

            repository.delete(usuario.get());

            return new ResponseEntity<>("Usuario removido com sucesso!", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Falha ao remover o usuario!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
