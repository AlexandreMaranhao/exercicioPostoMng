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
    public void cadastrarUsuario(@RequestBody @Valid UserRegisterDto inputedData){
        userRepository.save(new User(inputedData));
    }

    @GetMapping
    public List<UserListDto> listarUsuario(){

        return userRepository.findAll().stream().map(UserListDto::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizarUsuario(@RequestBody @Valid UserUpdateDto inputedData){
        var usuario = userRepository.getReferenceById(inputedData.id());
        usuario.updateUser(inputedData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirUsuario(@PathVariable Long id){
        var usuario = userRepository.getReferenceById(id);
        usuario.disable();
    }
}
