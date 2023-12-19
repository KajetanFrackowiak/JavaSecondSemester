package com.example.demo;

import com.example.demo.Exeptions.CatFoundException;
import com.example.demo.Exeptions.CatNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CatServiceTest {

    @Mock
    private CatRepository repository;
    private AutoCloseable openMocks;
    private CatService catService;

    @BeforeEach
    public void init() {
        openMocks = MockitoAnnotations.openMocks(this);
        catService = new CatService(repository);
    }

    @AfterEach
    public void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    public void findFinds() {
        String name = "Rafiki";
        Cat cat = new Cat(20, name);
        when(repository.findByName(name)).thenReturn(cat);

        Cat result = catService.getRepositoryByName(name);
        assertNull(result);
    }

    @Test
    public void saveSaves() {
        String name = "Rafiki";
        int age = 4;
        Cat cat = new Cat(age, name);
            ArgumentCaptor<Cat> captor = ArgumentCaptor.forClass(Cat.class);
            when(repository.save(captor.capture())).thenReturn(cat);

            catService.addCatToRepository(cat);
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
        catService.addCatToRepository(cat);
        });

    }
    @Test
    public void catUpdateThrowExceptionWhenCatIsPresent() {
        Cat cat = new Cat(5, "Guar");
        cat.setId(3);

        Cat catWithUpdate = new Cat(10, "Kapi");

        when(repository.findById(10)).thenReturn(Optional.of(cat));
        assertThrows(CatNotFoundException.class, () -> catService.updateCatByName(cat.getName(), catWithUpdate));
    }

    @Test
    public void testFilterByName() {
        Cat puszek = new Cat(1, "Puszek");
        Cat puszekOkruszek = new Cat(2, "Puszek Okruszek");
        Cat mrFluffy = new Cat(3, "Mr. Fluffy");

        List<Cat> allCats = List.of(puszek, puszekOkruszek, mrFluffy);

        when(repository.findAll()).thenReturn(allCats);

       List<Cat> result1 = catService.filterByName("Puszek");

       assertEquals(List.of(puszek, puszekOkruszek), result1);
    }
}
