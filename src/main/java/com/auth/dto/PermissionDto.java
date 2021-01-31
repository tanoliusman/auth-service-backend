package com.auth.dto;


import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDto {

    private Long id;

    @NotEmpty(message = "Permission name must not empty")
    private String permissionName;


}
