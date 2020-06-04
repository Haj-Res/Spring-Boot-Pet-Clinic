package com.hajres.petclinic.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {
    @RequestMapping({"", "/", "index", "index.html"})
    public String index() {
        log.info("Index controller - 'index' mapping");
        return "index";
    }

    @RequestMapping("/oups")
    public String oups() {
        return "notimplemented";
    }
}
