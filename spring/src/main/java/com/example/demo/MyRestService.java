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

    public void add(Cat cat) {
        repository.save(cat);
    }

}
