package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class MyRestService {
    CatRepository repository;


    public MyRestService(CatRepository repository) {
        this.repository = repository;
        this.repository.save(new Cat(5, "Erwin"));
        this.repository.save(new Cat(10, "Schrodinger"));
    }


    public Cat getRepositoryByName(String name) {
        return this.repository.findByName(name);
    }

    public void addCatToRepository(Cat cat) {
        repository.save(cat);
    }

    public void updateCatByName(String name, Cat updatedCat) {
        Cat existingCat = repository.findByName(name);

        if (existingCat != null) {
            existingCat.setName(updatedCat.getName());
            existingCat.setAge(updatedCat.getAge());

            repository.save(existingCat);
        }
    }


    public void deleteCatFromRepository(String name) {
        Cat existingCat = repository.findByName(name);

        if (existingCat != null) {
            repository.delete(existingCat);
        }
    }

}
