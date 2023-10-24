package com.example.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatServiceTest {
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

    @Test
    public void findFinds() {
        String name = "Rafiki";
        Cat cat = new Cat(20, name);
        Mockito.when(repository.findByName(name)).thenReturn(cat);

        Cat result = myRestService.getRepositoryByName(name);
        assertEquals(null, result);
    }

    @Test
    public void saveSaves() {
        String name = "Rafiki";
        int age = 4;
        Cat cat = new Cat(age, name);
            ArgumentCaptor<Cat> captor = ArgumentCaptor.forClass(Cat.class);
            Mockito.when(repository.save(captor.capture())).thenReturn(cat);

            myRestService.addCatToRepository(cat);
            Mockito.verify(repository, Mockito.times(1))
                    .save(Mockito.any());
            Cat catFromSaveCall = captor.getValue();
            assertEquals(cat, catFromSaveCall);

    }
}
