package com.example.epms.service;

import com.example.epms.dto.ProjectDto;
import com.example.epms.entity.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getAllProjects();

    Project getProjectById(Long id);

    Project addProject(ProjectDto projectDto);

    Project updateProject(Long id, ProjectDto projectDto);

    Project deleteProject(Long id);
}
