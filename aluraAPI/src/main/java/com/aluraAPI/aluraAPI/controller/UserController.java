package com.aluraAPI.aluraAPI.controller;

import com.aluraAPI.aluraAPI.domain.user.User;
import com.aluraAPI.aluraAPI.domain.user.UserRepository;

import com.aluraAPI.aluraAPI.domain.user.dto.UpdateUserDto;
import com.aluraAPI.aluraAPI.domain.user.dto.RegisterUserDto;
import com.aluraAPI.aluraAPI.domain.user.dto.ListUserDto;
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
    public void cadastrarUsuario(@RequestBody @Valid RegisterUserDto inputedData){
        userRepository.save(new User(inputedData));
    }

    @GetMapping
    public List<ListUserDto> listarUsuario(){

        return userRepository.findAll().stream().map(ListUserDto::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizarUsuario(@RequestBody @Valid UpdateUserDto inputedData){
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
