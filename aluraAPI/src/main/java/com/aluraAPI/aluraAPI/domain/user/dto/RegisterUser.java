package com.aluraAPI.aluraAPI.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterUser(
        @NotBlank
        String name,
        String user,
        String password,
        String permission
        ) {

}
