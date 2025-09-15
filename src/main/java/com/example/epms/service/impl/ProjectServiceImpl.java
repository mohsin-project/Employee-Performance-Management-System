package com.example.epms.service.impl;

import com.example.epms.custom_exception.NotFoundException;
import com.example.epms.dto.ProjectDto;
import com.example.epms.entity.Project;
import com.example.epms.mapper.ProjectMapper;
import com.example.epms.repository.ProjectRepository;
import com.example.epms.service.DepartmentService;
import com.example.epms.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    DepartmentService departmentService;

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(Long id) {
        if (id == null) return null;

        return projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project not found with id: " + id));
    }

    @Override
    public Project addProject(ProjectDto projectDto) {
        Project project = projectMapper.toEntity(projectDto);
        project.setDepartment(departmentService.getDepartmentById(projectDto.getDepartmentId()));

        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Long id, ProjectDto projectDto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project not found with id: " + id));

        if (projectDto.getDepartmentId() != null)
            project.setDepartment(departmentService.getDepartmentById(projectDto.getDepartmentId()));

        projectMapper.updateEntityFromDto(projectDto, project);
        return projectRepository.save(project);
    }

    @Override
    public Project deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project not found with id: " + id));

        project.getEmployeeProjects().clear();
        projectRepository.delete(project);
        return project;
    }
}
