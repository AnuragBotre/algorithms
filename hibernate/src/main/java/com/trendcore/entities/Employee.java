package com.trendcore.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by Anurag
 */
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME")
    private String name;

    /**
     * This is working.
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(name = "EMPLOYEE_PROJECTS",
            inverseJoinColumns = {@JoinColumn(name = "PROJECT_ID")},
            joinColumns = {@JoinColumn(name = "EMPLOYEE_ID")}
    )
    //This is not deleting link
    //@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    private List<Project> projects;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
