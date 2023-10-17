package com.example.MPR;

import com.example.MPR.dtos.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepo
        extends CrudRepository<Car, Long> {
    public List<Car> findByName(String name);
}
