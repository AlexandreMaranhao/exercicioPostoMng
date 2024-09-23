package com.aluraAPI.aluraAPI.domain.user.dto;

import com.aluraAPI.aluraAPI.domain.user.User;

public record ListUserDto(
        long id,
        String name,
        String user,
        String permission

) {
    public ListUserDto(User newUserInput) {
        this(newUserInput.getId(),
                newUserInput.getName(),
                newUserInput.getUser(),
                newUserInput.getPermission()
        );
    }
}
