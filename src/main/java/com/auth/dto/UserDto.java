package com.auth.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private List<RoleDto> roles;
}
