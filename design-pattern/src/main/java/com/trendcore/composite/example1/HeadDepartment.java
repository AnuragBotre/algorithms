package com.trendcore.composite.example1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class HeadDepartment implements Department{

    List<Department> departments = new ArrayList<>();

    @Override
    public String getName() {
        return "Head Dept";
    }

    @Override
    public void printDepartmentName() {
        departments.forEach(Department::printDepartmentName);
    }

    public void addDepartment(Department department){
        departments.add(department);
    }

    public void removeDepartment(Department department){
        int i = 0;
        boolean found = false;
        for (Department d : departments) {
            if(d.getName().equals(department.getName())){
                found = true;
                break;
            }
            i++;
        }

        if(found)
            departments.remove(i);
    }

}
