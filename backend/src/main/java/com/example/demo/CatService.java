package com.example.demo;

import com.example.demo.Exeptions.CatExceptionHandler;
import com.example.demo.Exeptions.CatFoundException;
import com.example.demo.Exeptions.CatNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CatService extends CatExceptionHandler {
    CatRepository repository;


    public CatService(CatRepository repository) {
        this.repository = repository;
        this.repository.save(new Cat(5, "Erwin"));
        this.repository.save(new Cat(10, "Schrodinger"));
    }


    public Cat getRepositoryByName(String name) {

        Optional<Cat> nameCat = Optional.ofNullable(this.repository.findByName(name));

        if (nameCat.isPresent()) {
            return this.repository.findByName(name);
        } else throw new CatNotFoundException();
    }

    public Cat getRepositoryByNameAndAge(String name, int age) {

        Optional<Cat> nameCat = Optional.ofNullable(this.repository.findCatByNameAndAge(name, age));

        if (nameCat.isPresent()) {
            return this.repository.findCatByNameAndAge(name, age);
        } else throw new CatNotFoundException();
    }

    public void addCatToRepository(Cat cat) {
        Cat myCat = this.repository.findCatByNameAndAge(cat.getName(), cat.getAge());

        if (myCat ==  null) {
            repository.save(cat);
        } else {
            throw new CatFoundException();
        }
    }  // post
    public Iterable<Cat> findAll() {
        return repository.findAll();
    }
    public Cat findCatById(int id) {

        Optional<Cat> nameCat = Optional.ofNullable(this.repository.findCatById(id));

        if (nameCat.isPresent()) {
            return this.repository.findCatById(id);
        } else throw new CatNotFoundException();
    }

    public void updateCatByName(String name, Cat updatedCat) {
        Cat existingCat = repository.findByName(name);
        Optional<Cat> updateCat = Optional.ofNullable(this.repository.findByName(name));

        if (updateCat.isPresent()) {
            existingCat.setName(updatedCat.getName());
            existingCat.setAge(updatedCat.getAge());
            repository.save(existingCat);
        } else throw new CatNotFoundException();
    }


    public void deleteCatFromRepository(String name, int age) {
        Cat existingCat = repository.findCatByNameAndAge(name, age);
        Optional<Cat> updateCat = Optional.ofNullable(repository.findCatByNameAndAge(name, age));

        if (updateCat.isPresent()) {
            repository.delete(existingCat);
        } else throw new CatNotFoundException();
    }

    public List<Cat> filterByName(String name) {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .filter(cat -> {
                    return cat.getName().contains(name);
                })
                .collect(Collectors.toList());
    }
}
