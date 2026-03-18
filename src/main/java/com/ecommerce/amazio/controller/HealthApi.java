package com.ecommerce.amazio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthApi {

    @GetMapping("/health")
    public ResponseEntity<String> activeRequest(){
        return new ResponseEntity<>("All Good", HttpStatus.OK);
    }
}
