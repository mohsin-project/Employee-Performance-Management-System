package com.example.epms.controller;

import com.example.epms.constant.PublicConstant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PublicConstant.PUBLIC)
public class PublicController {

    @GetMapping(PublicConstant.HEALTH_CHK)
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("ok");
    }
}
