package com.example.cardapio.controllers;

import com.example.cardapio.food.Food;
import com.example.cardapio.food.FoodRepository;
import com.example.cardapio.food.FoodRequestDTO;
import com.example.cardapio.food.FoodResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/food")
public class FoodController {
    private final FoodRepository repository;

    public FoodController(FoodRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody FoodRequestDTO data) {
        try {
            Food foodData = new Food(data);
            repository.save(foodData);
            return new ResponseEntity<>("Registro criado com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao criar o registro", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        Optional<Food> food = repository.findById(id);

        if (food.isPresent()) {
            repository.delete(food.get());
            return new ResponseEntity<>("Registro removido com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ID não localizado, registro não excluído!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<FoodResponseDTO> getAll() {
        return repository.findAll().stream().map(FoodResponseDTO::new).toList();
    }
}
