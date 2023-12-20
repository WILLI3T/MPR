package com.example.MPR;

import com.example.MPR.CarRepo;
import com.example.MPR.dtos.Car;
import com.example.MPR.services.MyRestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CarServiceTest {
    @Mock
    private CarRepo repository;
    private AutoCloseable openMocks;
    private MyRestService carService;

    @BeforeEach
    public void init() {
        openMocks = MockitoAnnotations.openMocks(this);
        carService = new MyRestService(repository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    public void findFinds() {
        String name = "test";
        int age = 1;
        Car car = new Car(name, age);
        Mockito.when(repository.findByName(name)).thenReturn(List.of(car));

        List<Car> result = carService.getCarByName(name);
        assertEquals(List.of(car), result);
    }

    @Test
    public void testSave() {
        String name = "name";
        int age = 1;
        Car car = new Car(name, age);
        ArgumentCaptor<Car> captor = ArgumentCaptor.forClass(Car.class);
        Mockito.when(repository.save(captor.capture())).thenReturn(car);

        carService.save(car);
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any());
        assertEquals(car, captor.getValue());
    }
    @Test
    public void testGetAllCars() {
        String name = "name";
        int age = 1;
        Car car = new Car(name, age);
        Car car2 = new Car("fiat", 2);
        List<Car> cars = new ArrayList<>();
        cars.add(car);
        cars.add(car2);
        Mockito.when(repository.findAll()).thenReturn(cars);

        Iterable<Car> result = carService.getAllCars();
        assertEquals(cars, result);
    }

}
