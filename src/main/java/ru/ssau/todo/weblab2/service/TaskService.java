package ru.ssau.todo.weblab2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;
import ru.ssau.todo.weblab2.dto.TaskPojo;
import ru.ssau.todo.weblab2.entity.Project;
import ru.ssau.todo.weblab2.entity.Task;
import ru.ssau.todo.weblab2.repository.ProjectRepository;
import ru.ssau.todo.weblab2.repository.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }
    public List<TaskPojo> getAllTasks(Long projectId) {
        List<Task> tasks = taskRepository.findByProject_Projectid(projectId);
        List<TaskPojo> tasksPojo = new ArrayList<>();
        for(Task task : tasks){
            tasksPojo.add(TaskPojo.fromEntity(task));
        }
        return tasksPojo;
    }
    
    public TaskPojo getTaskById(Long projectId, Long taskId) {
        try{
            return TaskPojo.fromEntity(taskRepository.findByProject_ProjectidAndTaskid(projectId, taskId));
        }catch(Exception ex){
            return null;
        }
    }

    public TaskPojo createTask(Long projectId, TaskPojo task) {
        Project project = projectRepository.findById(projectId)
                .orElse(null);
        if(project == null){
            return null;
        }

        Task newTask = TaskPojo.toEntity(task);
        newTask.setProject(project);
        newTask.setProjectid(projectId);

        return TaskPojo.fromEntity(taskRepository.save(newTask));
    }
    @Transactional
    public void deleteTask(Long projectId, Long taskId) {
        taskRepository.deleteByProject_ProjectidAndTaskid (projectId, taskId);
    }
    
    @Transactional
    public void deleteCompletedTasks(Long projectId) {
        taskRepository.deleteByProject_ProjectidAndIscompletedTrue(projectId);
    }
    @Transactional
    public TaskPojo updateTask(Long projectId, Long taskId, TaskPojo updatedTask) {
      Task existingTask = taskRepository.findByProject_ProjectidAndTaskid(projectId, taskId);
      if (existingTask == null) {
          return null;
      }
      Task task = TaskPojo.toEntity(updatedTask);

      task.setProject(existingTask.getProject());
      task.setProjectid(projectId);
      task.setTaskid(taskId);

      return TaskPojo.fromEntity(taskRepository.save(task));
    }
}
