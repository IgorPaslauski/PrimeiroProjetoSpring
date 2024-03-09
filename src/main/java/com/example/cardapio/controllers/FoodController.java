package com.example.cardapio.controllers;

import com.example.cardapio.food.Food;
import com.example.cardapio.food.FoodRepository;
import com.example.cardapio.food.FoodRequestDTO;
import com.example.cardapio.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("food")
public class FoodController {
    @Autowired
    private FoodRepository repository;

    @PostMapping
    public void create(@RequestBody FoodRequestDTO data){
        Food foodData = new Food(data);
        repository.save(foodData);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody long id){
        Optional<Food> food = repository.findById(id);
        ;
        if (food.isEmpty())
        {
            return new ResponseEntity<>("id não localizado, registro não exlcuido!", HttpStatus.BAD_REQUEST);
        }

        repository.delete(food.get());

        return new ResponseEntity<>("Registro removido com sucesso!", HttpStatus.OK);
    }

    @GetMapping
    public List<FoodResponseDTO> getAll(){
        List<FoodResponseDTO> lista = repository.findAll().stream().map(FoodResponseDTO::new).toList();

        return lista;
    }
}
