package com.auth.service;

import com.auth.domain.Permission;
import com.auth.dto.GeneralResponse;
import com.auth.dto.PermissionDto;

import java.util.List;
import java.util.Optional;

public interface PermissionService {
    List<PermissionDto> getAllPermissions();

    GeneralResponse createNewPermission(PermissionDto permissionDto);

    Optional<Permission> getPermissionById(Long id);
}
