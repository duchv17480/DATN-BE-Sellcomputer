package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.payload.response.DefaultResponse;
import com.example.udpm14sellcomputerpartsbackend.service.AuthService;
import com.example.udpm14sellcomputerpartsbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByUserId(@PathVariable("id") Long id){
        return ResponseEntity.ok(DefaultResponse.success(userService.findById(id)));
    }


    @PostMapping(value = "/update-image/{id}",consumes = "multipart/form-data")
    public ResponseEntity<?> updateImage(
            @PathVariable("id") Long id,
            @RequestBody MultipartFile file
    ){
        return ResponseEntity.ok(DefaultResponse.success("Update image success",userService.updateImage(id,file)));
    }




}
