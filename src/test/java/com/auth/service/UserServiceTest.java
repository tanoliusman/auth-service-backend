package com.auth.service;


import com.auth.AuthServiceApplication;
import com.auth.dto.PermissionDto;
import com.auth.dto.RoleDto;
import com.auth.dto.SignupRequestDto;
import com.auth.dto.UserPermissionsDto;
import com.auth.exceptions.AuthServiceException;
import com.auth.exceptions.UserNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServiceApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    private static boolean dataLoaded = false;

    @Before
    public void init() throws AuthServiceException, UserNotFoundException {
        if(!dataLoaded) {
            dataLoaded = true;
            PermissionDto permission1 = PermissionDto.builder().id(1l).permissionName("Permission1").build();
            PermissionDto permission2 = PermissionDto.builder().id(2l).permissionName("Permission2").build();
            PermissionDto permission3 = PermissionDto.builder().id(3l).permissionName("Permission3").build();
            PermissionDto permission4 = PermissionDto.builder().id(4l).permissionName("Permission4").build();
            PermissionDto permission5 = PermissionDto.builder().id(5l).permissionName("Permission5").build();
            permissionService.createNewPermission(permission1);
            permissionService.createNewPermission(permission2);
            permissionService.createNewPermission(permission3);
            permissionService.createNewPermission(permission4);
            permissionService.createNewPermission(permission5);
            roleService.createNewRole(RoleDto.builder().id(1l).name("Role1").permissions(Arrays.asList(permission1, permission2)).build());
            roleService.createNewRole(RoleDto.builder().id(2l).name("Role2").permissions(Arrays.asList(permission3, permission4)).build());
//            userService.registerUser(SignupRequestDto.builder().username("usman").email("usman@gmail.com").password("asdf123").build());
//            userService.registerUser(SignupRequestDto.builder().username("ali").email("ali@gmail.com").password("asdf123").build());
            userService.assignRolesToUser(1l, Arrays.asList(1l, 2l));
        }
    }

    @Test
    public void assignRolesTest() throws AuthServiceException {
        List<RoleDto> roles =userService.getUserRoles(1l);
        Assert.assertNotNull(roles);
        Assert.assertEquals(roles.size(),2);
    }

    @Test
    public void allowedPermissionTests() throws UserNotFoundException {
        UserPermissionsDto permissions =userService.getAllowedPermissions(1l,Arrays.asList(1l,2l,5l));
        Assert.assertNotNull(permissions);
        Assert.assertEquals(permissions.getAllowed().size(),2);
        Assert.assertEquals(permissions.getNotAllowed().size(),1);
    }

}
