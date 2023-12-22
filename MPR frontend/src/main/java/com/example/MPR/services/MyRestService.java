package com.example.MPR.services;

import com.example.MPR.dtos.Car;
import com.example.MPR.exceptions.CarNeedsToExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClient;


import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class MyRestService {


    private static final Logger log = LoggerFactory.getLogger(MyRestService.class);
    public static final String BASE_URL = "http://localhost:8080/api";
    RestClient restClient;
    @Autowired

    public MyRestService() {
        restClient = RestClient.create();
    }

//    public List<Car> getCarByName(String name) {
//        log.info("Fetching car(s) with name: {}", name);
//        return this.carRepo.findByName(name);
//    }

    public Iterable<Car> getAllCars() {
        log.info("Fetching all cars");
        List<Car> cars = restClient
                .get()
                .uri(BASE_URL + "/cars")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return cars;

    }

    public Car getCarById(Long id) {
        log.info("Fetching car with ID: {}", id);
        Car car = restClient
                .get()
                .uri(BASE_URL + "/cars/" + id)
                .retrieve()
                .body(Car.class);

        if (car != null) {
            return car;
        } else {
            throw new CarNeedsToExistException("Car with ID " + id + " does not exist.");
        }
    }

    public void save(Car car) {
        log.info("Saving a new car: {}", car);
        if (car.getName() == null || car.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Car name cannot be empty.");
        }
        if (car.getAge() == null) {
            throw new IllegalArgumentException("Car age cannot be empty.");
        }
        if (car.getAge() < 0) {
            throw new IllegalArgumentException("Car age cannot be negative.");
        } else if (car.getAge() == 0) {
            throw new IllegalArgumentException("Car age cannot be zero.");
        }

        ResponseEntity<Void> response = restClient
            .post()
            .uri(BASE_URL + "/cars")
            .contentType(APPLICATION_JSON)
            .body(car)
            .retrieve()
            .toBodilessEntity();



    log.info("Car saved successfully");
    }

    public void update(Car car) {
        log.info("Updating car: {}", car);
        if (car.getId() == null) {
            throw new CarNeedsToExistException("Car with ID " + car.getId() + " does not exist.");
        }
        restClient
            .put()
            .uri(BASE_URL + "/cars")
            .body(car)
            .retrieve()
            .toBodilessEntity();
    log.info("Car updated successfully");
    }



    public void deleteCar(Long id) {
        log.info("Deleting car with ID: {}", id);
        restClient
                .delete()
                .uri(BASE_URL + "/cars/" + id)
                .retrieve()
                .toBodilessEntity();
        log.info("Car with ID: {} deleted successfully", id);
    }
}

