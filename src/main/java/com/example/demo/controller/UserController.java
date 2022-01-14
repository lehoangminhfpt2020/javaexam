package com.example.demo.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;


@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired UserService userService ;
	@GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.gets()) ;
    }
    @PostMapping("/users")
    public ResponseEntity<?> getUsers(@RequestBody UserDto criteria , HttpServletRequest request) {
        return ResponseEntity.ok(userService.gets(criteria)) ;
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {   
        return Optional.ofNullable(userService.get(id)).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/user")
    public ResponseEntity<?> updateBarnPlan(@RequestBody UserDto user, HttpServletRequest request) {
    	return ResponseEntity.ok(userService.save(user));	
    }
      
    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
