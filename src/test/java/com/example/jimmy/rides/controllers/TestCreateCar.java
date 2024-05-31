package com.example.jimmy.rides.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.jimmy.rides.application.CreateCar.CreateCar;
import com.example.jimmy.rides.application.CreateCar.dto.CreateCarDto;
import com.example.jimmy.rides.application.DeleteCar.DeleteCar;
import com.example.jimmy.rides.application.ListCars.ListCars;
import com.example.jimmy.rides.application.UpdateCar.UpdateCar;
import com.example.jimmy.rides.infraestructure.controllers.CarController;
import com.fasterxml.jackson.databind.ObjectMapper;


//@AutoConfigureJdbc
//@SpringBootTest
@WebMvcTest(controllers = {CarController.class})
class TestCreateCarController {

@Autowired
private MockMvc mockMvc;

@MockBean
private CreateCar createCar;

@MockBean
private UpdateCar updateCar;

@MockBean
private DeleteCar deleteCar;

@MockBean
ListCars listCars;

@Test
public void ShouldCreateACar() throws Exception {

    String reqBody = new ObjectMapper().writeValueAsString(new CreateCarDto());

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/api/cars").param("model","Toyota").param("model","COROLLA").contentType(MediaType.APPLICATION_JSON_VALUE).content(reqBody)
                
                ).andExpect(MockMvcResultMatchers.status().isCreated());

    }
}
