package com.trendcore.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by Anurag
 */
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME")
    private String name;

    /**
     * If mappedBy is not written then hibernate will create another table
     * project_employees.
     * mappedBy = "projects" tells hibernate that this mapping is handled by
     * projects in the employee class.
     */
    //@ManyToMany(mappedBy = "projects",fetch = FetchType.EAGER)
    //@ManyToMany(fetch = FetchType.EAGER)

    /**
     * This is working
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(name = "EMPLOYEE_PROJECTS",
            joinColumns = {@JoinColumn(name = "PROJECT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "EMPLOYEE_ID")}
    )
    //This is not deleting link
    //@ManyToMany(mappedBy = "projects",fetch = FetchType.EAGER , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Employee> employees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
