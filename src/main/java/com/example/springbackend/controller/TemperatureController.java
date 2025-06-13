package com.example.springbackend.controller;

import com.example.springbackend.model.Reading;
import com.example.springbackend.repository.ReadingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/temperature")
@CrossOrigin(origins = "*")
public class TemperatureController {

    private final ReadingRepository repository;

    public TemperatureController(ReadingRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Reading add(@RequestBody Reading reading) {
        return repository.save(reading);
    }

    @GetMapping
    public List<Reading> getAll() {
        return repository.findAll();
    }
}
