package com.auth.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class UserPermissionsDto {

    List<PermissionDto> allowed;
    List<PermissionDto> notAllowed;

    public UserPermissionsDto(List<PermissionDto> allowed, List<PermissionDto> notAllowed) {
        this.allowed = allowed;
        this.notAllowed = notAllowed;
    }
}
