package com.aluraAPI.aluraAPI.domain.user;

import com.aluraAPI.aluraAPI.domain.user.dto.UserUpdateDto;
import com.aluraAPI.aluraAPI.domain.user.dto.UserRegisterDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "user")
@Entity(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String user;
    private String password;
    private String permission;
    private boolean active;


    public User(@Valid UserRegisterDto registerUserInput) {
        this.name = registerUserInput.name();
        this.user = registerUserInput.user();
        this.password = registerUserInput.password();
        this.permission = registerUserInput.permission();
        this.active = true;
    }


    public void updateUser(@Valid UserUpdateDto updateUserInput) {
        if (updateUserInput.name() != null){
            this.name = updateUserInput.name();
        }
        if (updateUserInput.user() != null){
            this.user = updateUserInput.user();
        }
        if (updateUserInput.password() != null){
            this.password = updateUserInput.password();
        }
        if (updateUserInput.permission() != null){
            this.permission = updateUserInput.permission();
        }
    }

    public void disable() {this.active = false;}

}
