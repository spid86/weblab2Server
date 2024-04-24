package ru.ssau.todo.weblab2.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.ssau.todo.weblab2.dto.ProjectPojo;
import ru.ssau.todo.weblab2.dto.TaskPojo;
import ru.ssau.todo.weblab2.service.ProjectService;
import ru.ssau.todo.weblab2.service.TaskService;

@RestController
@RequestMapping("/api/projects")
public class ControllerMain {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TaskService taskService;

    @GetMapping("/{projectId}/tasks")
    public List<TaskPojo> getAllTasks(@PathVariable Long projectId) {
        return taskService.getAllTasks(projectId);
    }
    @GetMapping("/{projectId}/tasks/{taskId}")
    public ResponseEntity<TaskPojo> getTaskById(@PathVariable Long projectId, @PathVariable Long taskId) {
        TaskPojo taskPojo = taskService.getTaskById(projectId,taskId);
        if(taskPojo != null){
            return new ResponseEntity<>(taskPojo, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    } 
    @PostMapping("/{projectId}/tasks")
    public ResponseEntity<TaskPojo> createTask(@PathVariable Long projectId, @RequestBody TaskPojo taskPojo) {
        TaskPojo task = taskService.createTask(projectId, taskPojo);
        if(task == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(task, HttpStatus.CREATED);
        }
        
    }  
    @PutMapping("/{projectId}/tasks/{taskId}")
    public ResponseEntity<TaskPojo> updateTask(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody TaskPojo taskPojo) {
        TaskPojo updatedTask = taskService.updateTask(projectId, taskId, taskPojo);
        if(updatedTask !=null){
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }  
    @DeleteMapping("/{projectId}/tasks/{taskId}")
    public ResponseEntity<TaskPojo> deleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        taskService.deleteTask(projectId, taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
    @DeleteMapping("/{projectId}/tasks")
    public ResponseEntity<TaskPojo> deleteCompletedTasks(@PathVariable Long projectId) {
        taskService.deleteCompletedTasks(projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 

//////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable long projectId) {

        ProjectPojo prj = projectService.getProjectById(projectId);//ProjectRepository.getProjectById(projectId);
        if(prj != null){
            return new ResponseEntity<>(prj, HttpStatus.OK);
        } else{
            ErrorResponse errorResponse = new ErrorResponse("Project not found", HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
     public ResponseEntity<List<ProjectPojo>> searchForProjects(
            @RequestParam(name = "search") String searchString) {
        List<ProjectPojo> projectsPojo = projectService.findProjectsBySearch(searchString);
        return new ResponseEntity<>(projectsPojo, HttpStatus.CREATED);
    }
    
    @PostMapping
    public ResponseEntity<ProjectPojo> createProject(@RequestBody ProjectPojo project) {
        ProjectPojo createdProject = projectService.createProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }
    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectPojo> updateProject(@PathVariable Long projectId, @RequestBody ProjectPojo project) {
        ProjectPojo updatedProject = projectService.updateProject(projectId, project);//ProjectRepository.updateProject(projectId, project);
        if (updatedProject != null) {
            return new ResponseEntity<>(updatedProject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/incomplete")
    public List<Map<Long, Integer>> findOpenTaskCountInProjects() {
        return projectService.findOpenTaskCountInProjects();
    }
    
}

