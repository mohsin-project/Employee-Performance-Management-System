package com.example.epms.controller;


import com.example.epms.constant.EmployeeConstant;
import com.example.epms.dto.AddProjectToEmployeeDto;
import com.example.epms.dto.EmployeeDto;
import com.example.epms.mapper.EmployeeMapper;
import com.example.epms.service.EmployeeProjectService;
import com.example.epms.service.EmployeeService;
import com.example.epms.validation.ValidationGroup;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(EmployeeConstant.EMP)
public class EmployeeController {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeProjectService employeeProjectService;

    @GetMapping(EmployeeConstant.EMP_ALL)
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeMapper.toDtoList(employeeService.getAllEmployees()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeMapper.toDto(employeeService.getEmployeeById(id)));
    }

    @Transactional
    @PostMapping(EmployeeConstant.EMP_ADD)
    public ResponseEntity<EmployeeDto> addEmployee(@Validated(ValidationGroup.Create.class)
                                                   @RequestBody
                                                   EmployeeDto employeeDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeMapper.toDto(employeeService.addEmployee(employeeDto)));
    }

    @Transactional
    @PutMapping(EmployeeConstant.EMP_UPDATE)
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id,

                                                      @Validated(ValidationGroup.Update.class)
                                                      @RequestBody
                                                      EmployeeDto employeeDto) {
        return ResponseEntity.accepted()
                .body(employeeMapper.toDto(employeeService.updateEmployee(id, employeeDto)));
    }

    @Transactional
    @PutMapping(EmployeeConstant.EMP_ADD_PRJS)
    public ResponseEntity<EmployeeDto> addProjects(@PathVariable Long id,

                                                   @RequestBody
                                                   @NotEmpty
                                                   Set<@Valid AddProjectToEmployeeDto> projects) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(employeeMapper.toDto(employeeProjectService.addProjectsForEmployee(id, projects)));
    }

    @Transactional
    @PutMapping(EmployeeConstant.EMP_REMOVE_PRJS)
    public ResponseEntity<EmployeeDto> removeProjects(@PathVariable Long id,

                                                      @RequestBody
                                                      @NotEmpty
                                                      Set<Long> projectIds) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(employeeMapper.toDto(employeeProjectService.removeProjectsFromEmployee(id, projectIds)));
    }

    @Transactional
    @DeleteMapping(EmployeeConstant.EMP_DELETE)
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeMapper.toDto(employeeService.deleteEmployee(id)));
    }
}
