package com.trendcore.composite.example1;

public class ClientForCompositeExample1 {

    public static void main(String[] args) {
        HeadDepartment department = new HeadDepartment();

        department.addDepartment(new ComputerDepartment());
        department.addDepartment(new ManagementDept());

        department.printDepartmentName();
    }

}
