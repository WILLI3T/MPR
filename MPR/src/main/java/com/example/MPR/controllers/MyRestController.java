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
   public Iterable<Car> getAllCars() {
       return myRestService.getAllCars();
   }
    @GetMapping("/cars/{name}")
    public List<Car> findCarByName(@PathVariable("name") String name) {
        return myRestService.getCarByName(name);
    }

    @PutMapping("/cars")
    public void updateCar(@RequestBody Car car) {
        if (myRestService.getCarById(car.getId()).isEmpty()){
            System.out.println("Nie ma takiego autka");
        }else {
            myRestService.save(car);
            System.out.println("Zaktualizowano auto");
        }

    }

    @PostMapping("/cars")
    public void addCar(@RequestBody Car car) {
        if (myRestService.getCarById(car.getId()).isEmpty()){
            myRestService.save(car);
            System.out.println("Dodano auto do listy");

        }else {
            System.out.println("Jest już takie autko");
        }
    }

   @DeleteMapping("/cars/{id}")
   public void deleteCar(@PathVariable Long id) {
       myRestService.deleteCar(id);
   }
}


