package com.example.BootJPABoard.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BoardApiController {
    @GetMapping("/test")
    public String test(){
        throw new RuntimeException("Holy! Exception..");
    }
    //127.0.0.1:8070/api/test
}
