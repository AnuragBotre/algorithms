package com.trendcore;

import com.trendcore.entities.Employee;
import com.trendcore.entities.Project;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Anurag
 */
public class ManyToManyApplication {

    public static void main(String[] args) {
        org.hibernate.SessionFactory sessionFactory = com.trendcore.SessionFactory.getInstance().getSessionFactory();
        Session session = sessionFactory.openSession();
        System.out.println(sessionFactory);

        Scanner scanner = new Scanner(System.in);

        boolean flag = true;
        while (flag) {
            String line = scanner.nextLine();
            try {

                switch (line) {
                    case "createEmployee":
                        createEmployee();
                        break;

                    case "createProject":
                        createProject();
                        break;

                    case "fetchProjects":
                        fetchProjects();
                        break;

                    case "loadEmployee":
                        loadEmployee();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void loadEmployee() {
        Session session = SessionFactory.getInstance().getSessionFactory().openSession();

        Scanner scanner = new Scanner(System.in);

        Project project = (Project) session.load(Project.class, scanner.nextInt());

        //System.out.println(project);

        //System.out.println(project.getId());

        session.close();
    }

    private static void createProject() {
        Session session = SessionFactory.getInstance().getSessionFactory().openSession();
        session.beginTransaction();

        Project project = new Project();
        project.setName("MySql");
        session.save(project);
        session.getTransaction().commit();
    }

    private static void fetchProjects() {
        Session session = SessionFactory.getInstance().getSessionFactory().openSession();

        Scanner scanner = new Scanner(System.in);

        Project project = (Project) session.get(Project.class, scanner.nextInt());

        session.close();

        System.out.println(project.getEmployees());

        session = SessionFactory.getInstance().getSessionFactory().openSession();
        session.beginTransaction();
        //session.save(project);
        session.update(project);
        project.getEmployees().remove(0);
        session.getTransaction().commit();

    }

    private static void createEmployee() {
        Session session = SessionFactory.getInstance().getSessionFactory().openSession();

        session.getTransaction().begin();

        Employee employee = new Employee();
        employee.setName("Anurag");

        Project project = new Project();
        project.setName("Hibernate");

        Project project1 = new Project();
        project1.setName("Spring");

        List<Project> projects = new ArrayList<>();
        projects.add(project);
        projects.add(project1);

        employee.setProjects(projects);

        session.persist(employee);
        session.getTransaction().commit();
        session.close();
    }
}
