package com.aluraAPI.aluraAPI.domain.user.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateUserDto(
        @NotNull
        Long id,
        String name,
        String user,
        String password,
        String permission

) {

}
