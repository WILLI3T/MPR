package com.example.MPR.controllers;

import com.example.MPR.dtos.Car;
import com.example.MPR.services.MyRestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.security.PublicKey;
import java.util.Optional;

@Controller
public class WebController {

    @GetMapping(value = "/welcome")
    public String getWelcomeView() {
        return "welcome";
    }
    private final MyRestService myRestService;
    @Autowired
    public WebController(MyRestService myRestService) {
        this.myRestService = myRestService;
        myRestService.save(new Car("test", 1));
        myRestService.save(new Car("test2", 2));
    }
    @GetMapping(value = "/index")
    public String getIndexView(Model model) {
        model.addAttribute("cars", myRestService.getAllCars());
        return "index";
    }
    @GetMapping(value = "/addCar")
    public String getAddView(Model model) {
        model.addAttribute("car", new Car("", null));
        return "addCar";
    }
    @PostMapping("/addCar")
    public String addCar(Car car, Model model, RedirectAttributes redirectAttributes) {
        String errorMessage = validateCar(car);
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            return "addCar";
        }

        myRestService.save(car);
        redirectAttributes.addFlashAttribute("successMessage", "Car added successfully");
        return "redirect:/index";
    }

    private String validateCar(Car car) {
        if (car.getName() == null || car.getName().trim().isEmpty()) {
            return "Car name cannot be empty.";
        }
        if (car.getAge() == null) {
            return "Car age cannot be empty.";
        }
        if (car.getAge() < 0) {
            return "Car age cannot be negative.";
        }
        if (car.getAge() == 0) {
            return "Car age cannot be zero.";
        }
        return null;
    }


    @GetMapping(value = "/editCar/{id}")
    public String getEditCarView(@PathVariable("id") long id, Model model) {
        Optional<Car> car = myRestService.getCarById(id);
        if (car.isPresent()) {
            model.addAttribute("car", car.get());
            return "editCar";
        } else {
            return "redirect:/index";
        }
    }

    @PostMapping(value = "/editCar")
    public String postEditCarView(Car car, Model model, RedirectAttributes redirectAttributes) {
        String errorMessage = validateCar(car);
        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
            return "editCar";
        }

        myRestService.save(car);
        redirectAttributes.addFlashAttribute("successMessage", "Car updated successfully");
        return "redirect:/index";
    }

    @GetMapping(value = "/deleteCar/{id}")
    public String deleteCar(@PathVariable("id") long id) {
        myRestService.deleteCar(id);
        return "redirect:/index";
    }

}


