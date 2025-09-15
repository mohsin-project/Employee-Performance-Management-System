package com.example.epms.controller;

import com.example.epms.constant.DepartmentConstant;
import com.example.epms.dto.DepartmentDto;
import com.example.epms.mapper.DepartmentMapper;
import com.example.epms.service.DepartmentService;
import com.example.epms.validation.ValidationGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(DepartmentConstant.DEPT)
public class DepartmentController {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    DepartmentService departmentService;

    @GetMapping(DepartmentConstant.DEPT_ALL)
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        return ResponseEntity.ok(departmentMapper.toDtoList(departmentService.getAllDepartments()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentMapper.toDto(departmentService.getDepartmentById(id)));
    }

    @Transactional
    @PostMapping(DepartmentConstant.DEPT_ADD)
    public ResponseEntity<DepartmentDto> addDepartment(@Validated(ValidationGroup.Create.class)
                                                       @RequestBody
                                                       DepartmentDto departmentDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(departmentMapper.toDto(departmentService.addDepartment(departmentDto)));
    }

    @Transactional
    @PutMapping(DepartmentConstant.DEPT_UPDATE)
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id,

                                                          @Validated(ValidationGroup.Update.class)
                                                          @RequestBody
                                                          DepartmentDto departmentDto) {
        return ResponseEntity.accepted()
                .body(departmentMapper.toDto(departmentService.updateDepartment(id, departmentDto)));
    }

    @Transactional
    @DeleteMapping(DepartmentConstant.DEPT_DELETE)
    public ResponseEntity<DepartmentDto> deleteDepartment(@PathVariable Long id) {
        return ResponseEntity.ok(departmentMapper.toDto(departmentService.deleteDepartment(id)));
    }
}
