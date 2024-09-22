package com.aluraAPI.aluraAPI.domain.user.dto;

import com.aluraAPI.aluraAPI.domain.user.User;

public record ListUser(
        long id,
        String name,
        String user,
        String permission

) {
    public ListUser(User newUserInput) {
        this(newUserInput.getId(),
                newUserInput.getName(),
                newUserInput.getUser(),
                newUserInput.getPermission()
        );
    }
}
