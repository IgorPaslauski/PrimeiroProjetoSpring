package com.example.cardapio.controllers;

import com.example.cardapio.usuario.Usuario;
import com.example.cardapio.usuario.UsuarioRepository;
import com.example.cardapio.usuario.UsuarioRequestDTO;
import com.example.cardapio.usuario.UsuarioResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Controller("/")
public class UsuarioController {

    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<String> create (@RequestBody UsuarioRequestDTO data){
        try {
            Usuario login = new Usuario(data);
            repository.save(login);
            return new ResponseEntity<>("Usuario criado com sucesso!", HttpStatus.CREATED);
        } catch (Exception ex)
        {
            return new ResponseEntity<>("Falha ao criar o usuario!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<UsuarioResponseDTO> getAll(){
        return repository.findAll().stream().map(UsuarioResponseDTO::new).toList();
    }
}
