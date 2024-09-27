package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.user.User;
import com.aluraAPI.aluraAPI.domain.user.UserRepository;

import com.aluraAPI.aluraAPI.domain.user.dto.UserUpdateDto;
import com.aluraAPI.aluraAPI.domain.user.dto.UserRegisterDto;
import com.aluraAPI.aluraAPI.domain.user.dto.UserListDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public void registerUser(@RequestBody @Valid UserRegisterDto registerUserInput){
        userRepository.save(new User(registerUserInput));
    }

    @GetMapping
    public List<UserListDto> listUser(){

        return userRepository.findAll().stream().map(UserListDto::new).toList();
    }

    @PutMapping
    @Transactional
    public void updateUser(@RequestBody @Valid UserUpdateDto updateUserInput){
        var user = userRepository.getReferenceById(updateUserInput.id());
        user.updateUser(updateUserInput);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void inactivateUser(@PathVariable Long id){
        var user = userRepository.getReferenceById(id);
        user.disable();
    }
}
