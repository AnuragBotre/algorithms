package com.trendcore.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by Anurag
 */
public class EmployeeBean {

    private String fullName;

    /**
     * Auto wiring + Qualifier examaple.
     */
    /*@Autowired
    @Qualifier("finance")
    private DepartmentBean departmentBean;*/


    /*private DepartmentBean departmentBean;
    public EmployeeBean(DepartmentBean humanResource){
        this.departmentBean = humanResource;
    }*/


    //Constructor
    /*private DepartmentBean departmentBean;

    @Autowired
    public EmployeeBean(@Qualifier("humanResource") DepartmentBean departmentBean){
        this.departmentBean = departmentBean;
    }*/


    private DepartmentBean departmentBean;

    public DepartmentBean getDepartmentBean() {
        return departmentBean;
    }

    @Autowired
    @Qualifier("finance")
    public void setDepartmentBean(DepartmentBean departmentBean) {
        this.departmentBean = departmentBean;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
