package com.auth.service.impl;

import com.auth.domain.Permission;
import com.auth.dto.GeneralResponse;
import com.auth.dto.PermissionDto;
import com.auth.repository.PermissionRepository;
import com.auth.service.PermissionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public List<PermissionDto> getAllPermissions() {
        return permissionRepository.findAll().stream().map(permission -> objectMapper.convertValue(permission,PermissionDto.class)).collect(Collectors.toList());
    }

    @Override
    public GeneralResponse createNewPermission(PermissionDto permissionDto) {
        permissionRepository.save(objectMapper.convertValue(permissionDto, Permission.class));
        return new GeneralResponse(true, "Permission created successfully");
    }

    @Override
    public Optional<Permission> getPermissionById(Long id) {
        return permissionRepository.findById(id);
    }
}
