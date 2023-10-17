package com.example.MPR.services;

import com.example.MPR.CarRepo;
import com.example.MPR.dtos.Car;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class MyRestService {


    private final CarRepo carRepo;

    public MyRestService(CarRepo repo) {
        this.carRepo = repo;
    }
    public List<Car> getCarByName(String name){
        return this.carRepo.findByName(name);
    }

   public Iterable<Car> getAllCars() {
       return carRepo.findAll();
   }

    public void save(Car car) {
        carRepo.save(car);
    }
    public Optional<Car> getCarById(Long id){
        return carRepo.findById(id);
    }

   public void deleteCar(Long id) {
       carRepo.deleteById(id);
       System.out.println("Usunięto samochód o id:"+ id);
   }
}


