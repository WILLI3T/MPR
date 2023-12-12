package com.example.MPR.controllers;

import com.example.MPR.dtos.Car;
import com.example.MPR.services.MyRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.PublicKey;

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
        model.addAttribute("car", new Car("", 0));
        return "addCar";
    }
    @PostMapping(value = "/addCar")
    public String postAddView(Car car) {
        myRestService.save(car);
        return "redirect:/index";
    }

}
