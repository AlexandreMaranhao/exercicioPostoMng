package com.aluraAPI.aluraAPI.domain.user.dto;

import com.aluraAPI.aluraAPI.domain.user.User;

public record UserListDto(
        long id,
        String name,
        String user,
        String permission

) {
    public UserListDto(User listUser) {
        this(listUser.getId(),
                listUser.getName(),
                listUser.getUser(),
                listUser.getPermission()
        );
    }
}
