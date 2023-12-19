package com.example.demo;

import com.example.demo.Controller.MyController;
import com.example.demo.Exeptions.CatExceptionHandler;
import com.example.demo.Exeptions.CatFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CatControllerTest
{
    private MockMvc mockMvc;

    @Mock
    private CatService catService;
    private AutoCloseable openMocks;
    @InjectMocks
    private MyController controller;

    @BeforeEach
    public void setup() {
        openMocks = MockitoAnnotations.openMocks(this);
        controller = new MyController(catService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(
                new CatExceptionHandler(), controller).build();
    }
    @AfterEach
    public void tearDown() throws Exception {
        openMocks.close();
    }
    @Test
    public void check400IsReturnedWhenCatisAlreadyThere() throws  Exception {
        doThrow(new CatFoundException()).when(catService).addCatToRepository(any());

        mockMvc.perform(MockMvcRequestBuilders.post("/cat/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Kapi\", \"age\": 3}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getByIdReturns200WhenCatIsPresent() throws Exception {
        Cat cat = new Cat(4, "Igorek");
        when(catService.findCatById(4)).thenReturn(cat);

        mockMvc.perform(MockMvcRequestBuilders.get("/cat/get/4"))
                .andExpect(jsonPath("$.get").value(4))
                .andExpect(jsonPath("$.name").value("Igorek"))
                .andExpect(status().isOk());
    }
}
