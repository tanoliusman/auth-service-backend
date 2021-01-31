package com.auth.service.impl;

import com.auth.domain.Permission;
import com.auth.domain.Role;
import com.auth.domain.User;
import com.auth.dto.*;
import com.auth.exceptions.AuthServiceException;
import com.auth.exceptions.ErrorResponseEnum;
import com.auth.exceptions.UserNotFoundException;
import com.auth.repository.PermissionRepository;
import com.auth.repository.RoleRepository;
import com.auth.repository.UserRepository;
import com.auth.security.JwtUtils;
import com.auth.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users =  userRepository.findAll();
        return users.stream().map(user-> objectMapper.convertValue(user,UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(String id) {
        Optional<User> user =userRepository.findById(Long.parseLong(id));
        return user.map(u-> objectMapper.convertValue(u, UserDto.class)).orElse(null);
    }

    @Override
    public GeneralResponse assignRolesToUser(Long id, List<Long> roleIds) throws UserNotFoundException {
        User user =userRepository.findById(id).map(dbUser-> {
            for(Long roleId: roleIds){
                roleRepository.findById(roleId).map(role-> dbUser.getRoles().add(role));
            }
            return dbUser;
        }).orElseThrow(UserNotFoundException::new);
        userRepository.save(user);
        return new GeneralResponse(true, "Roles assigned to user successfully");
    }

    @Override
    public List<RoleDto> getUserRoles(Long id) throws AuthServiceException {
        List<RoleDto> roleDtos = new ArrayList<>();
        User user =userRepository.findById(id).map(dbUser-> {
            for (Role role : dbUser.getRoles()) {
                roleDtos.add(objectMapper.convertValue(role,RoleDto.class));
            }
            return dbUser;
        }).orElseThrow(()->{return new AuthServiceException(ErrorResponseEnum.USER_NOT_FOUND, HttpStatus.BAD_REQUEST);});
        return roleDtos;
    }

    @Override
    public UserPermissionsDto getAllowedPermissions(Long id, List<Long> permissionIds) throws UserNotFoundException {

        List<PermissionDto> allowed = new ArrayList<>();
        List<PermissionDto> notAllowed = new ArrayList<>();
        User user =userRepository.findById(id).map( u->u).orElseThrow(UserNotFoundException::new);
        List<Permission> permissions = permissionRepository.findAllById(permissionIds);
        List<Long> userPermissionIds =  new ArrayList<>();
        user.getRoles().stream().forEach(role-> {
            userPermissionIds.addAll(roleRepository.findById(role.getId())
                    .map(r-> r.getPermissions().stream().map(p->p.getId()).collect(Collectors.toList())
                            ).orElse(new ArrayList<>()));
        });
        permissions.stream().forEach(permission -> (userPermissionIds.contains(permission.getId())?allowed:notAllowed)
                            .add(objectMapper.convertValue(permission, PermissionDto.class)));
        return new UserPermissionsDto(allowed, notAllowed);
    }

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) throws AuthServiceException {
        Authentication authentication;
        try {
            authentication= authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (Exception ex){
            throw new AuthServiceException(ErrorResponseEnum.AUTHENTIACTION_ERROR, HttpStatus.BAD_REQUEST);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return new JwtResponse(jwt);

    }

    @Override
    public JwtResponse registerUser(SignupRequestDto signUpRequestDto) throws AuthServiceException {
        if (userRepository.existsByUsername(signUpRequestDto.getUsername())) {
            throw new AuthServiceException(ErrorResponseEnum.USERNAME_ALREADY_TAKEN, HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequestDto.getEmail())) {
           throw new AuthServiceException(ErrorResponseEnum.EMAIL_ALREADY_TAKEN, HttpStatus.BAD_REQUEST);
        }
        User user = objectMapper.convertValue(signUpRequestDto,User.class);
        user.setPassword(encoder.encode(user.getPassword()));

        userRepository.save(user);

        return authenticateUser(new LoginRequest(signUpRequestDto.getUsername(), signUpRequestDto.getPassword()));
    }
}
