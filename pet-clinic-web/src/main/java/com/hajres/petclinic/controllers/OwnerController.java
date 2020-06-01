package com.hajres.petclinic.controllers;

import com.hajres.petclinic.model.Owner;
import com.hajres.petclinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String listOwner(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }
}
