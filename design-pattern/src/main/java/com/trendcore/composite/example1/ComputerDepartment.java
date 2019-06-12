package com.trendcore.composite.example1;

public class ComputerDepartment implements Department{

    @Override
    public String getName() {
        return "Computer Dept";
    }

    @Override
    public void printDepartmentName() {
        System.out.println("Computer Department...");
    }
}
