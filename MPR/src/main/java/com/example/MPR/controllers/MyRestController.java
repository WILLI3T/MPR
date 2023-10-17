package com.example.MPR.controllers;

import com.example.MPR.dtos.Car;
import com.example.MPR.services.MyRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    private final MyRestService myRestService;

    @Autowired
    public MyRestController(MyRestService myRestService) {
        this.myRestService = myRestService;
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return myRestService.getAllCars();
    }

    @PutMapping("/cars")
    public void addCar(@RequestBody Car car) {
        myRestService.addCar(car);
    }

    @DeleteMapping("/cars/{name}")
    public void deleteCar(@PathVariable String name) {
        myRestService.deleteCar(name);
    }
}

