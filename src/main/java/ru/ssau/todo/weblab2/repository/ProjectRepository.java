package ru.ssau.todo.weblab2.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ru.ssau.todo.weblab2.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT p FROM Project p WHERE LOWER(p.projectname) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(p.projectdescription) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Project> findBySearchTerm(@Param("search") String search);
    
    @Query("SELECT p.projectid as projectId, COUNT(t) as openTaskCount FROM Project p RIGHT JOIN p.tasks t WHERE t.iscompleted = false GROUP BY p.projectid")
    List<Map<Long, Integer>> findOpenTaskCountInProjects();
} 
