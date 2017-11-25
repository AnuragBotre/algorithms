package com.trendcore;

import com.trendcore.entities.Employee;
import com.trendcore.entities.Project;
import com.trendcore.entities.UserDetails;
import com.trendcore.entities.Vehicle;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * Created by Anurag
 */
public class SessionFactory {

    private volatile static SessionFactory sessionFactory;

    private org.hibernate.SessionFactory hibernateSessionFactory;

    public static SessionFactory getInstance() {
        if (sessionFactory == null) {
            synchronized (SessionFactory.class) {
                if (sessionFactory == null) {
                    sessionFactory = new SessionFactory();
                }
            }
        }

        return sessionFactory;
    }

    public org.hibernate.SessionFactory getSessionFactory() {

        if(hibernateSessionFactory == null){
            Configuration configuration = new AnnotationConfiguration()
                    .configure()
                    .addAnnotatedClass(UserDetails.class)
                    .addAnnotatedClass(Vehicle.class)
                    .addAnnotatedClass(Employee.class)
                    .addAnnotatedClass(Project.class);

            //.addPackage("com.trendcore.entities");

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            hibernateSessionFactory = configuration.buildSessionFactory(builder.build());
        }
        return hibernateSessionFactory;
    }

}
