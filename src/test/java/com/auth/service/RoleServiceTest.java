package com.auth.service;


import com.auth.AuthServiceApplication;
import com.auth.domain.Role;
import com.auth.dto.PermissionDto;
import com.auth.dto.RoleDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServiceApplication.class)
public class RoleServiceTest {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    private static boolean isLoaded = false;

    @Before
    public void init(){
        if(!isLoaded) {
            isLoaded = true;
            permissionService.createNewPermission(PermissionDto.builder().permissionName("Permission 1").build());
            permissionService.createNewPermission(PermissionDto.builder().permissionName("Permission 2").build());
        }
    }

    @Test
    public void createRoleTest(){
        roleService.createNewRole(RoleDto.builder().name("Role1").permissions(Arrays.asList(
                PermissionDto.builder().id(1l).build(),
                PermissionDto.builder().id(2l).build()
        )).build());
        RoleDto roleDto = roleService.getRoleById(1l);
        Assert.assertNotNull(roleDto);
    }


    @Test
    public void checkRolePermissions(){
        roleService.createNewRole(RoleDto.builder().name("Role2").permissions(Arrays.asList(
                PermissionDto.builder().id(1l).build(),
                PermissionDto.builder().id(2l).build()
        )).build());
        RoleDto roleDto = roleService.getRoleById(1l);
        Assert.assertNotNull(roleDto);
        Assert.assertEquals(roleDto.getPermissions().size(),2);
    }
}
