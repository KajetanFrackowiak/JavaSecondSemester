package com.example.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.awt.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CatServiceTest {
    private MockMvc mockMvc;
    @Mock
    private CatRepository repository;
    private AutoCloseable openMocks;
    private MyRestService myRestService;



    @BeforeEach
    public void init() {
        openMocks = MockitoAnnotations.openMocks(this);
        myRestService = new MyRestService(repository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        openMocks.close();
    }

    @InjectMocks
    private MyController controller;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(
                new CatExceptionHandler(), controller).build();
    }

    @Test
    public void getByIdReturns200WhenCatIsPresent() throws Exception {
        Cat cat = new Cat(4, "Igorek");
        when(repository.findById(4)).thenReturn(Optional.of(cat));

        mockMvc.perform(MockMvcRequestBuilders.get("/cat/get/4"))
                .andExpect(jsonPath("$.get").value(4))
                .andExpect(jsonPath("$.name").value("Igorek"))
                .andExpect(status().isOk());
    }


    @Test
    public void findFinds() {
        String name = "Rafiki";
        Cat cat = new Cat(20, name);
        when(repository.findByName(name)).thenReturn(cat);

        Cat result = myRestService.getRepositoryByName(name);
        assertNull(result);
    }

    @Test
    public void saveSaves() {
        String name = "Rafiki";
        int age = 4;
        Cat cat = new Cat(age, name);
            ArgumentCaptor<Cat> captor = ArgumentCaptor.forClass(Cat.class);
            when(repository.save(captor.capture())).thenReturn(cat);

            myRestService.addCatToRepository(cat);
            Mockito.verify(repository, Mockito.times(1))
                    .save(any());
            Cat catFromSaveCall = captor.getValue();
            assertEquals(cat, catFromSaveCall);

    }

    @Test
    public void catAddThrowExceptionWhenCatIsPresent() {
        Cat cat = new Cat(4, "Rafiki");
        cat.setId(2);

        when(repository.findById(2)).thenReturn(Optional.of(cat));

        assertThrows(CatFoundException.class, () -> {
        myRestService.addCatToRepository(cat);
        });

    }
    @Test
    public void catUpdateThrowExceptionWhenCatIsPresent() {
        Cat cat = new Cat(5, "Guar");
        cat.setId(3);

        Cat catWithUpdate = new Cat(10, "Kapi");

        when(repository.findById(10)).thenReturn(Optional.of(cat));
        assertThrows(CatNotFoundException.class, () -> myRestService.updateCatByName(cat.getName(), catWithUpdate));
    }

    @Test
    public void check400IsReturnedWhenCatisAlreadyThere() throws  Exception {
        doThrow(new CatFoundException()).when(myRestService).addCatToRepository(any());

        mockMvc.perform(MockMvcRequestBuilders.post("/cat/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Kapi\", \"age\": 3}")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    }

}
