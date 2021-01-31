package com.auth.service;


import com.auth.AuthServiceApplication;
import com.auth.domain.Permission;
import com.auth.dto.PermissionDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServiceApplication.class)
public class PermissionServiceTest {

    @Autowired
    private PermissionService permissionService;

    @Test
    public void createPermission(){
        permissionService.createNewPermission(PermissionDto.builder().permissionName("Permission 1").build());
        Optional<Permission> permission =permissionService.getPermissionById(1l);
        Assert.assertTrue(permission.isPresent());
    }
}
