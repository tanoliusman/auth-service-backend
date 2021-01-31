package com.auth.service.impl;

import com.auth.domain.Role;
import com.auth.dto.GeneralResponse;
import com.auth.dto.RoleDto;
import com.auth.repository.PermissionRepository;
import com.auth.repository.RoleRepository;
import com.auth.service.PermissionService;
import com.auth.service.RoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PermissionService permissionService;

    @Override
    public RoleDto getRoleById(Long id) {
        return roleRepository.findById(id)
                .map(role-> objectMapper.convertValue(role, RoleDto.class)).orElse(new RoleDto());
    }

    @Override
    public List<RoleDto> findAllRoles() {
        return roleRepository.findAll().stream().map(role-> objectMapper.convertValue(role, RoleDto.class)).collect(Collectors.toList());
    }

    @Override
    public GeneralResponse createNewRole(RoleDto roleDto) {
        roleDto.setPermissions(roleDto.getPermissions().stream().filter(permissionDto -> permissionService.getPermissionById(permissionDto.getId()).isPresent())
                .collect(Collectors.toList()));
        roleRepository.save(objectMapper.convertValue(roleDto, Role.class));
        return new GeneralResponse(true, "Role Created Successfully");
    }

    @Override
    public List<RoleDto> getRolesByRoleIds(Long[] roleIds) {
        if(roleIds!= null){
            return (List<RoleDto>) roleRepository.findAllById(Collections.arrayToList(roleIds)).stream()
                    .map(role-> objectMapper.convertValue(role,RoleDto.class)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
