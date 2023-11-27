package com.example.MPR.services;

import com.example.MPR.exceptions.CarAlreadyExistsException;
import com.example.MPR.CarRepo;
import com.example.MPR.dtos.Car;
import com.example.MPR.exceptions.CarNeedsToExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class MyRestService {

    private final CarRepo carRepo;
    private static final Logger log = LoggerFactory.getLogger(MyRestService.class);

    @Autowired
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
        if (car.getName() == null || car.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nazwa samochodu nie może być pusta.");
        }
        if (car.getId() != null) {
            throw new IllegalArgumentException("Nowy samochód nie powinien mieć ustawionego ID.");
        }


        carRepo.save(car);
    }

    public void update(Car car) {
        if (car.getId() == null || !carRepo.existsById(car.getId())) {
            throw new CarNeedsToExistException("Samochód z ID " + car.getId() + " nie istnieje.");
        }
        carRepo.save(car);
    }

    public Optional<Car> getCarById(Long id){
        Optional<Car> car = carRepo.findById(id);

        if(car.isPresent()){
            return car;
        }
        else{
            throw new CarNeedsToExistException("Samochód z ID " + id + " nie istnieje.");
        }
    }

    public void deleteCar(Long id) {
        if (!carRepo.existsById(id)) {
            throw new CarNeedsToExistException("Samochód z ID " + id + " nie istnieje.");
        }
        carRepo.deleteById(id);
        log.info("Usunięto samochód o id: {}", id);
    }
}
