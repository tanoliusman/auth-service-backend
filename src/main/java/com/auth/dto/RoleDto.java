package com.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private Long id;

    @NotEmpty(message = "Role name is required")
    private String name;

    @NotNull( message = "Must provide permissions")
    @Size(min=1, message = "Must provide atleast one Permission")
    private List<PermissionDto> permissions;

}
