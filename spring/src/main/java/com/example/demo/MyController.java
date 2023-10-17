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

    @PostMapping("cat/add")
    public void addCat(
            @RequestBody Cat cat) {
        this.service.add(cat);
    }

}
