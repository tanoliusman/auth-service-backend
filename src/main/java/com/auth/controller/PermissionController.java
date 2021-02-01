package com.auth.controller;


import com.auth.dto.GeneralResponse;
import com.auth.dto.PermissionDto;
import com.auth.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Validated
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public ResponseEntity<List<PermissionDto>> getAllPermissions(){
        return ResponseEntity.ok(permissionService.getAllPermissions());
    }

    @PostMapping
    public ResponseEntity<GeneralResponse> createNewPermission(@Valid  @RequestBody PermissionDto permissionDto){
        return ResponseEntity.ok(permissionService.createNewPermission(permissionDto));
    }
}
