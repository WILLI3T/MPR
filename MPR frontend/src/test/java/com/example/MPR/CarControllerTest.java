package com.example.MPR;

import com.example.MPR.controllers.RestResponceEntituExceptionHandler;
import com.example.MPR.dtos.Car;
import com.example.MPR.exceptions.CarNeedsToExistException;
import com.example.MPR.services.MyRestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.is;

import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@ExtendWith(MockitoExtension.class)
public class CarControllerTest {
    private MockMvc mockMvc;
    @Mock
    private MyRestService service;
    @InjectMocks
    private MyRestController Controller;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(new RestResponceEntituExceptionHandler(),Controller).build();
    }
    @Test
    public void getByIdReturns200WhenCarExists() throws Exception {
        Car car = new Car("test", 1);
        when(service.getCarById(1L)).thenReturn(Optional.of(car));

        mockMvc.perform(get("/api/cars/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    public void getByIdReturns404WhenCarDoesNotExist() throws Exception {
        Mockito.lenient().when(service.getCarById(1L)).thenThrow(new CarNeedsToExistException("Samochód z ID 1 nie istnieje."));

        mockMvc.perform(get("/api/cars/1"))
                .andExpect(status().isNotFound());
    }

}
