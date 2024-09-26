package com.aluraAPI.aluraAPI.domain.user.dto;

import jakarta.validation.constraints.NotNull;

public record UserUpdateDto(
        @NotNull
        Long id,
        String name,
        String user,
        String password,
        String permission

) {

}
