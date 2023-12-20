package com.example.MPR.controllers;

import com.example.MPR.dtos.Car;
import com.example.MPR.services.MyRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class MyRestController {

    private final MyRestService myRestService;

    @Autowired
    public MyRestController(MyRestService myRestService) {
        this.myRestService = myRestService;
        myRestService.save(new Car("test", 1));
        myRestService.save(new Car("test2", 2));
        myRestService.save(new Car("test3", 3));

    }

    @GetMapping
    public ResponseEntity<Iterable<Car>> getAllCars() {
        return ResponseEntity.ok(myRestService.getAllCars());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Car>> findCarByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(myRestService.getCarByName(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity <Optional<Car>> getCarById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(myRestService.getCarById(id));
    }

    @PutMapping
    public ResponseEntity<String> updateCar(@RequestBody Car car) {
        myRestService.update(car);
        return ResponseEntity.ok("Samochód zaktualizowany");
    }

    @PostMapping
    public ResponseEntity<String> addCar(@RequestBody Car car) {
        myRestService.save(car);
        return ResponseEntity.status(HttpStatus.CREATED).body("Dodano samochód");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        myRestService.deleteCar(id);
        return ResponseEntity.ok("Usunięto samochód o id: " + id);
    }
}
