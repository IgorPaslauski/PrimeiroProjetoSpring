package com.example.cardapio.controllers;

import com.example.cardapio.food.Food;
import com.example.cardapio.food.FoodRepository;
import com.example.cardapio.food.FoodRequestDTO;
import com.example.cardapio.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<FoodResponseDTO> getAll(){
        List<FoodResponseDTO> lista = repository.findAll().stream().map(FoodResponseDTO::new).toList();

        return lista;
    }
}
