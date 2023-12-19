package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class WebController {
    @GetMapping(value = "/welcome")
    public String getWelcomeView() {
        return "welcome";
    }

    private final CatService service;

    @Autowired
    public WebController(CatService catService) {
        this.service = catService;
    }

    @GetMapping(value = "/index")
    public String getIndexView(Model model) {
        model.addAttribute("cats", service.findAll());
        return "index";
    }

    @GetMapping(value = "/addCat")
    public String getAddView(Model model) {
        model.addAttribute("cat", new Cat(0, ""));
        return "addCat";
    }

    @PostMapping(value = "/addCat")
    public String addCat(Cat cat, Model model, RedirectAttributes redirectAttributes) {
        if (cat.getAge() < 0) {
            model.addAttribute("errorMessage", "Age cannot be less than 0");
            return "addCat";
        }
        service.addCat(cat);
        redirectAttributes.addFlashAttribute("successMessage", "Cat");
        return "redirect:/index";
    }
//
//    @GetMapping(value = "/editCat/{id}")
//    public String getEditView(@PathVariable("id") int id, Model model) {
//        Cat cat = service.findCatById(id);
//        if (cat!=null) {
//            model.addAttribute("cat", cat);
//        }
//        return "editCat";
//    }


}
