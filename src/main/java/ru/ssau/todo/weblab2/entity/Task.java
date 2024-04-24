package ru.ssau.todo.weblab2.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "task", schema = "public")
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long taskid;
    private long projectid;
    private String taskname;
    private String taskdescription;

    @Temporal(TemporalType.DATE)
    @Column(name = "plannedenddate", nullable = false)
    private Date plannedenddate;

    private boolean iscompleted; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ProjectID", insertable = false, updatable = false, referencedColumnName = "projectid")
    private Project project;
}
