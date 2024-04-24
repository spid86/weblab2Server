package ru.ssau.todo.weblab2.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



import lombok.Getter;
import lombok.Setter;
import ru.ssau.todo.weblab2.entity.Project;
import ru.ssau.todo.weblab2.entity.Task;

@Getter
@Setter
public class ProjectPojo {
    private long projectid;
    private String projectname;
    private String projectdescription;
    private Date startdate;
    private Date enddate;
    private List<TaskPojo> tasks;

public static ProjectPojo fromEntity(Project prj){
    ProjectPojo projectPojo = new ProjectPojo();
    projectPojo.setProjectid(prj.getProjectid());
    projectPojo.setProjectname(prj.getProjectname());
    projectPojo.setProjectdescription(prj.getProjectdescription());
    projectPojo.setStartdate(prj.getStartdate());
    projectPojo.setEnddate(prj.getEnddate());
    if(prj.getTasks() == null){
        return projectPojo;
    }
    List<TaskPojo> tasksPojo = new ArrayList<>();
    for(Task task : prj.getTasks()){
        tasksPojo.add(TaskPojo.fromEntity(task));
    }
    projectPojo.setTasks(tasksPojo);
    return projectPojo;
}
public static Project toEntity(ProjectPojo projectPojo){
Project prj = new Project();
    prj.setProjectid(projectPojo.getProjectid());
    prj.setProjectname(projectPojo.getProjectname());
    prj.setProjectdescription(projectPojo.getProjectdescription());
    prj.setStartdate(projectPojo.getStartdate());
    prj.setEnddate(projectPojo.getEnddate());
    if(projectPojo.getTasks() == null){
        return prj;
    }
    List<Task> tasks = new ArrayList<>();
    for(TaskPojo taskPojo : projectPojo.getTasks()){
        tasks.add(TaskPojo.toEntity(taskPojo));
    }
    prj.setTasks(tasks);
    return prj;
}
}
