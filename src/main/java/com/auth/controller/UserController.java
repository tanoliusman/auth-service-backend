package com.auth.controller;


import com.auth.dto.GeneralResponse;
import com.auth.dto.RoleDto;
import com.auth.dto.UserPermissionsDto;
import com.auth.exceptions.AuthServiceException;
import com.auth.exceptions.UserNotFoundException;
import com.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping
//    public ResponseEntity<?> getAllUsers() {
//        return ResponseEntity.ok(userService.getAllUsers());
//    }
//
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> authenticateUser(@PathVariable("id") String id) {
//        return ResponseEntity.ok(userService.getUserById(id));
//    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<GeneralResponse> assignRolesToUser(@PathVariable("id") @Valid @Min(value=1,message="User Id is required") Long id,
                                                             @RequestBody @Valid @NotNull() @Size(min=1,message="Must have atleast 1 role it in list") List<Long> roleIds) throws UserNotFoundException {
        return ResponseEntity.ok(userService.assignRolesToUser(id, roleIds));
    }

    @GetMapping("/{id}/roles")
    public ResponseEntity<List<RoleDto>> getUserRoles(@PathVariable("id") Long id) throws AuthServiceException {
        return ResponseEntity.ok(userService.getUserRoles(id));
    }


    @PostMapping("/{id}/permissions")
    public ResponseEntity<UserPermissionsDto> getAllowedPermissions(@PathVariable("id") Long id,
                                                                    @RequestBody List<Long> permissionIds) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getAllowedPermissions(id, permissionIds));
    }


}
