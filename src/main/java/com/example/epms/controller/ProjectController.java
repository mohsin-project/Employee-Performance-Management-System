package com.example.epms.controller;


import com.example.epms.constant.ProjectConstant;
import com.example.epms.dto.AddEmployeeToProjectDto;
import com.example.epms.dto.ProjectDto;
import com.example.epms.mapper.ProjectMapper;
import com.example.epms.service.EmployeeProjectService;
import com.example.epms.service.ProjectService;
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
@RequestMapping(ProjectConstant.PRJ)
public class ProjectController {

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    ProjectService projectService;

    @Autowired
    EmployeeProjectService employeeProjectService;

    @GetMapping(ProjectConstant.PRJ_ALL)
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        return ResponseEntity.ok(projectMapper.toDtoList(projectService.getAllProjects()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectMapper.toDto(projectService.getProjectById(id)));
    }

    @Transactional
    @PostMapping(ProjectConstant.PRJ_ADD)
    public ResponseEntity<ProjectDto> addProject(@Validated(ValidationGroup.Create.class) @RequestBody ProjectDto projectDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(projectMapper.toDto(projectService.addProject(projectDto)));
    }

    @Transactional
    @PutMapping(ProjectConstant.PRJ_UPDATE)
    public ResponseEntity<ProjectDto> updateProject(@PathVariable Long id, @Validated(ValidationGroup.Update.class) @RequestBody ProjectDto projectDto) {
        return ResponseEntity.accepted()
                .body(projectMapper.toDto(projectService.updateProject(id, projectDto)));
    }

    @Transactional
    @PutMapping(ProjectConstant.PRJ_ADD_EMPS)
    public ResponseEntity<ProjectDto> addEmployees(@PathVariable Long id, @RequestBody @NotEmpty Set<@Valid AddEmployeeToProjectDto> employees) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(projectMapper.toDto(employeeProjectService.addEmployeesForProject(id, employees)));
    }

    @Transactional
    @PutMapping(ProjectConstant.PRJ_REMOVE_EMPS)
    public ResponseEntity<ProjectDto> removeEmployees(@PathVariable Long id, @RequestBody @NotEmpty Set<Long> employeesIds) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(projectMapper.toDto(employeeProjectService.removeEmployeesFromProject(id, employeesIds)));
    }

    @Transactional
    @DeleteMapping(ProjectConstant.PRJ_DELETE)
    public ResponseEntity<ProjectDto> deleteProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectMapper.toDto(projectService.deleteProject(id)));
    }
}
