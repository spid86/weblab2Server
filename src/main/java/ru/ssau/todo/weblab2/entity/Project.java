package ru.ssau.todo.weblab2.entity;

import lombok.Getter;
import lombok.Setter;


import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "project", schema = "public")
@Getter
@Setter
public class Project{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projectid;
    private String projectname;
    private String projectdescription;

    @Temporal(TemporalType.DATE)
    @Column(name = "startdate")
    private Date startdate;

    @Temporal(TemporalType.DATE)
    @Column(name = "enddate")
    private Date enddate; 

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Task> tasks;

    @PrePersist
    @PreUpdate
    private void validateDates() {
        if (startdate != null && enddate != null && startdate.after(enddate)) {
            throw new IllegalArgumentException("End date must be after or equal to start date");
        }
    }
}