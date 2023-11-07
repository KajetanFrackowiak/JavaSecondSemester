package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatRepository extends CrudRepository<Cat, Integer> {
    public Cat findByName(String name);
    public Cat findCatByNameAndAge(String name, int age);
    public Cat findCatById(int id);
}
