package com.auth.service;

import com.auth.dto.*;
import com.auth.exceptions.AuthServiceException;
import com.auth.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserById(String id);

    GeneralResponse assignRolesToUser(Long id, List<Long> roleIds) throws UserNotFoundException;

    List<RoleDto> getUserRoles(Long id) throws AuthServiceException;

    UserPermissionsDto getAllowedPermissions(Long id, List<Long> permissionIds) throws UserNotFoundException;

    JwtResponse authenticateUser(LoginRequest loginRequest) throws AuthServiceException;

    JwtResponse registerUser(SignupRequestDto signUpRequestDto) throws AuthServiceException;
}
