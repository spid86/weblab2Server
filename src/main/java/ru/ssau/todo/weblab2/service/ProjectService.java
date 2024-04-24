package ru.ssau.todo.weblab2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ru.ssau.todo.weblab2.dto.ProjectPojo;

import ru.ssau.todo.weblab2.entity.Project;
import ru.ssau.todo.weblab2.repository.ProjectRepository;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectPojo> getAllProjects() {
        List<Project> projList = projectRepository.findAll();
        List<ProjectPojo> projListPojo = new ArrayList<>();
        for(Project prj : projList){
            projListPojo.add(ProjectPojo.fromEntity(prj));
        }
        return projListPojo;
    }

    public ProjectPojo getProjectById(Long projectId) {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        if (projectOptional.isPresent()) {
            return ProjectPojo.fromEntity(projectOptional.get());
    }else {
        return null;
    }
}
    public ProjectPojo createProject(ProjectPojo newProject) {
        return ProjectPojo.fromEntity(projectRepository.save(ProjectPojo.toEntity(newProject)));
    }
    public ProjectPojo updateProject(Long projectId, ProjectPojo updatedProject) {
        
        Project project = projectRepository.findById(projectId)
                .orElse(null);
        if(project == null){
            return null;
        }
        Project updProject = ProjectPojo.toEntity(updatedProject);

        updProject.setProjectid(projectId);
        updProject.setTasks(project.getTasks());

        return ProjectPojo.fromEntity(projectRepository.save(updProject));
    }
    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }
    public List<Map<Long, Integer>> findOpenTaskCountInProjects() {
        return projectRepository.findOpenTaskCountInProjects();
    }
    public List<ProjectPojo> findProjectsBySearch(String search) {
       List<Project> projList = projectRepository.findBySearchTerm(search);
        List<ProjectPojo> projListPojo = new ArrayList<>();
        for(Project prj : projList){
            projListPojo.add(ProjectPojo.fromEntity(prj));
        }
        return projListPojo;
    }

}
