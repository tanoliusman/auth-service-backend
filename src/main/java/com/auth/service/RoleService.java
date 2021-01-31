package com.auth.service;

import com.auth.dto.GeneralResponse;
import com.auth.dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto getRoleById(Long id);

    List<RoleDto> findAllRoles();

    GeneralResponse createNewRole(RoleDto roleDto);

    List<RoleDto> getRolesByRoleIds(Long[] roleIds);
}
