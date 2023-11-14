package com.example.MPR;

import com.example.MPR.controllers.MyRestController;
import com.example.MPR.dtos.Car;
import com.example.MPR.services.MyRestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CarControllerTest {
    private MockMvc mockMvc;
    @Mock
    private MyRestService service;
    @InjectMocks
    private MyRestController controller;

    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    public void testGetAllCars() throws Exception {
        Car car = new Car("test", 1);
        mockMvc.perform(get("/api/cars")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(1)));



    }




}
