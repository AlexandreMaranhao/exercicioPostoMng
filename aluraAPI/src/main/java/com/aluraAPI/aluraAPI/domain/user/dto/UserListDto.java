package com.aluraAPI.aluraAPI.domain.user.dto;

import com.aluraAPI.aluraAPI.domain.user.User;

public record UserListDto(
        long id,
        String name,
        String user,
        String permission

) {
    public UserListDto(User newUserInput) {
        this(newUserInput.getId(),
                newUserInput.getName(),
                newUserInput.getUser(),
                newUserInput.getPermission()
        );
    }
}
