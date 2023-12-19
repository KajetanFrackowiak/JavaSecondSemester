package com.example.demo;

import com.example.demo.Exceptions.CatExceptionHandler;
import com.example.demo.Exceptions.CatFoundException;
import com.example.demo.Exceptions.CatNotFoundException;
import org.apache.catalina.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@Service
public class CatService extends CatExceptionHandler {

    public static final String API_URL = "http://localhost:8080/";
    RestClient restCat;

    public CatService() {
        restCat = RestClient.create();
    }

//    public Iterable<Demo> findAll() {
//
//        return this.repository.findAll();
//    }

    public Iterable<Cat> findAll() {
        List<Cat> cats = restCat
                .get()
                .uri(API_URL + "cats")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
        return cats;
    }

    public void addCat(Cat cat) {
        ResponseEntity<Void> response = restCat.post()
                .uri(API_URL + "cat/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(cat)
                .retrieve()
                .toBodilessEntity();
    }
//    public CatService(CatRepository repository) {
//        this.repository = repository;
//        this.repository.save(new Cat(5, "Erwin"));
//        this.repository.save(new Cat(10, "Schrodinger"));
//    }
//
//
//    public Cat getRepositoryByName(String name) {
//
//        Optional<Cat> nameCat = Optional.ofNullable(this.repository.findByName(name));
//
//        if (nameCat.isPresent()) {
//            return this.repository.findByName(name);
//        } else throw new CatNotFoundException();
//    }
//
//    public Cat getRepositoryByNameAndAge(String name, int age) {
//
//        Optional<Cat> nameCat = Optional.ofNullable(this.repository.findCatByNameAndAge(name, age));
//
//        if (nameCat.isPresent()) {
//            return this.repository.findCatByNameAndAge(name, age);
//        } else throw new CatNotFoundException();
//    }
//
//    public void addCatToRepository(Cat cat) {
//        Cat myCat = this.repository.findCatByNameAndAge(cat.getName(), cat.getAge());
//
//        if (myCat ==  null) {
//            repository.save(cat);
//        } else {
//            throw new CatFoundException();
//        }
//    }  // post
//    public Iterable<Cat> findAll() {
//        return repository.findAll();
//    }
//    public Cat findCatById(int id) {
//
//        Optional<Cat> nameCat = Optional.ofNullable(this.repository.findCatById(id));
//
//        if (nameCat.isPresent()) {
//            return this.repository.findCatById(id);
//        } else throw new CatNotFoundException();
//    }
//
//    public void updateCatByName(String name, Cat updatedCat) {
//        Cat existingCat = repository.findByName(name);
//        Optional<Cat> updateCat = Optional.ofNullable(this.repository.findByName(name));
//
//        if (updateCat.isPresent()) {
//            existingCat.setName(updatedCat.getName());
//            existingCat.setAge(updatedCat.getAge());
//            repository.save(existingCat);
//        } else throw new CatNotFoundException();
//    }
//
//
//    public void deleteCatFromRepository(String name, int age) {
//        Cat existingCat = repository.findCatByNameAndAge(name, age);
//        Optional<Cat> updateCat = Optional.ofNullable(repository.findCatByNameAndAge(name, age));
//
//        if (updateCat.isPresent()) {
//            repository.delete(existingCat);
//        } else throw new CatNotFoundException();
//    }
//
//    public List<Cat> filterByName(String name) {
//        return StreamSupport.stream(repository.findAll().spliterator(), false)
//                .filter(cat -> {
//                    return cat.getName().contains(name);
//                })
//                .collect(Collectors.toList());
//    }
}
