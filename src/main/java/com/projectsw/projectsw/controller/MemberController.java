package com.projectsw.projectsw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MemberController {

    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "<html><body style='background-color:white;'><h1>Boas vindas</h1></body></html>";
    }


}
