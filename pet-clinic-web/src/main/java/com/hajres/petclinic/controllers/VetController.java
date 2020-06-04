package com.hajres.petclinic.controllers;

import com.hajres.petclinic.service.VetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/vets")
public class VetController {
    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listVets(Model model) {
        log.info("Vet Controller - 'index' mapping");
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }
}
