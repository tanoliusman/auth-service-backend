package com.auth.controller;


import com.auth.dto.GeneralResponse;
import com.auth.dto.RoleDto;
import com.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getRoleById(@PathVariable("id") @Valid @Min(value = 1,message="Role Id is required")  Long id) {
//        return ResponseEntity.ok(roleService.getRoleById(id));
//    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAvailableRolesWithIds(@RequestParam("ids") @Valid @Min(value = 1,message="Role Id is required") Long[] roleIds){
        return ResponseEntity.ok(roleService.getRolesByRoleIds(roleIds));
    }


    @PostMapping
    public ResponseEntity<GeneralResponse> createNewRole(@Valid @RequestBody RoleDto roleDto) {

        return ResponseEntity.ok(roleService.createNewRole(roleDto));
    }

}
