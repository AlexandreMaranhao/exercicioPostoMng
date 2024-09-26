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


    public User(@Valid UserRegisterDto newUserInput) {
        this.name = newUserInput.name();
        this.user = newUserInput.user();
        this.password = newUserInput.password();
        this.permission = newUserInput.permission();
        this.active = true;
    }


    public void updateUser(@Valid UserUpdateDto newUserInput) {
        if (newUserInput.name() != null){
            this.name = newUserInput.name();
        }
        if (newUserInput.user() != null){
            this.user = newUserInput.user();
        }
        if (newUserInput.password() != null){
            this.password = newUserInput.password();
        }
        if (newUserInput.permission() != null){
            this.permission = newUserInput.permission();
        }
    }

    public void disable() {this.active = false;}

}
