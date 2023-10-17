package com.example.MPR.services;

import com.example.MPR.dtos.Car;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyRestService {

    private final List<Car> cars;

    public MyRestService() {
        cars = new ArrayList<>();
        cars.add(new Car("Toyota", 5));
        cars.add(new Car("Ford", 3));
    }

    public List<Car> getAllCars() {
        return cars;
    }

    public void addCar(Car car) {
        cars.add(car);
        System.out.println("Dodano auto do listy");
    }

    public void deleteCar(String name) {
        cars.removeIf(car -> car.getName().equals(name));
    }
}


