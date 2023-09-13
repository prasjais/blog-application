package com.blogapplication.controllers;

import com.blogapplication.entities.User;
import com.blogapplication.payloads.ApiResponse;
import com.blogapplication.payloads.UserDTO;
import com.blogapplication.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody  UserDTO userDTO)
    {
        return ResponseEntity.ok(this.userServices.createUser(userDTO));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO,@PathVariable("userId") Integer id)
    {
        return ResponseEntity.ok(this.userServices.updateUser(userDTO,id));
    }

    @GetMapping("/")
    public List<UserDTO> getAllUser()
    {
        return this.userServices.getAllUser();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("userId") Integer id)
    {
        return ResponseEntity.ok(this.userServices.getUser(id));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer id)
    {
        this.userServices.deleteUser(id);
//        return new ResponseEntity(Map.of("message","User Deleted Successfully"), HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }

}
