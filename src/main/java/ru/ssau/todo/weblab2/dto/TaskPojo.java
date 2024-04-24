package ru.ssau.todo.weblab2.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import ru.ssau.todo.weblab2.entity.Task;


@Getter
@Setter
public class TaskPojo {
    private long taskid;
    private long projectid;
    private String taskname;
    private String taskdescription;
    private Date plannedenddate;
    private boolean iscompleted;
    
    public static TaskPojo fromEntity(Task task){
        TaskPojo taskPojo = new TaskPojo();
        taskPojo.setTaskid(task.getTaskid());
        taskPojo.setProjectid(task.getProjectid());
        taskPojo.setPlannedenddate(task.getPlannedenddate());
        taskPojo.setTaskdescription(task.getTaskdescription());
        taskPojo.setTaskname(task.getTaskname());
        taskPojo.setIscompleted(task.isIscompleted());
        return taskPojo;
    }
    public static Task toEntity(TaskPojo taskPojo){
        Task task = new Task();
        task.setTaskid(taskPojo.getTaskid());
        task.setProjectid(taskPojo.getProjectid());
        task.setPlannedenddate(taskPojo.getPlannedenddate());
        task.setTaskdescription(taskPojo.getTaskdescription());
        task.setTaskname(taskPojo.getTaskname());
        task.setIscompleted(taskPojo.isIscompleted());
        return task;
    }

}
