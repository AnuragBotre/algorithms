package com.trendcore.composite.example1;

public class ManagementDept implements Department{

    @Override
    public String getName() {
        return "Management Dept";
    }

    @Override
    public void printDepartmentName() {
        System.out.println("Management Department..");
    }
}
