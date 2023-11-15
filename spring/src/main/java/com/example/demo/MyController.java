package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyController {

    private MyRestService service;

    public MyController(MyRestService service) {
        this.service = service;
    }

    @GetMapping("cat/{name}")
    public Cat getCatByName(
            @PathVariable("name") String name
    ) {
        return this.service.getRepositoryByName(name);
    }

    @GetMapping("cat/{name}")
    public Cat getCatByNameAndAge(
            @PathVariable("name") String name, int age
    ) {
        return this.service.getRepositoryByNameAndAge(name, age);
    }
    @GetMapping("cat/{name}")
    public Cat getCatById(
            @PathVariable("name") int id
    ) {
        return this.service.findCatById(id);
    }

    @PostMapping("cat/add") // <--
    public void addCatToRepository(
            @RequestBody Cat cat) {
        this.service.addCatToRepository(cat);
    }

    @PutMapping("/cat/update/{name}")
    public void updateCatInRepository(@PathVariable String name, @RequestBody Cat updatedCat) {
        service.updateCatByName(name, updatedCat);
    }


    @DeleteMapping("cat/delete/{name}")
    public void deleteCatInRepository(
            @PathVariable String name, int age) {
        this.service.deleteCatFromRepository(name, age);
    }

}
