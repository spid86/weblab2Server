package ru.ssau.todo.weblab2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;
import ru.ssau.todo.weblab2.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProject_Projectid(Long projectId);

    Task findByProject_ProjectidAndTaskid(Long projectId, Long taskId);

    @Transactional
    void deleteByProject_ProjectidAndTaskid (Long projectId, Long taskId);

    @Transactional
    void deleteByProject_ProjectidAndIscompletedTrue(Long projectId);

}
