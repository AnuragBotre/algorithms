package com.trendcore;

import com.trendcore.entities.Address;
import com.trendcore.entities.HomeAddress;
import com.trendcore.entities.UserDetails;
import com.trendcore.entities.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Anurag
 */
public class Application {

    public static void main(String[] args) {
        SessionFactory sessionFactory = com.trendcore.SessionFactory.getInstance().getSessionFactory();
        Session session = sessionFactory.openSession();
        System.out.println(sessionFactory);

        Scanner scanner = new Scanner(System.in);

        boolean flag = true;
        while (flag) {
            String line = scanner.nextLine();
            try {

                switch (line) {
                    case "createUser":
                        createUser();
                        break;
                    case "createUserWithVechicle":
                        createUserWithVehicle();
                        break;
                    case "createUserWithVehicleOneToMany":
                        createUserWithVehicleOneToMany();
                        break;
                    case "fetchUser":
                        fetchUser();
                        break;
                    case "fetchVehicle":
                        fetchVehicle();
                        break;
                    case "createVehicle":
                        createVehicle();
                        break;
                    case "createVehicleWithUser":
                        createVehicleWithUser();
                        break;
                    case "update":
                        break;
                    case "delete":
                        break;
                    case "exit":
                        flag = false;
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private static void createUserWithVehicleOneToMany() {
        SessionFactory sessionFactory = com.trendcore.SessionFactory.getInstance().getSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        UserDetails userDetails = getUserDetails();

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleName("Car");
        vehicle.setUserDetails(userDetails);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVehicleName("Audi");
        vehicle1.setUserDetails(userDetails);

        Collection<Vehicle> vehicleCollection = new ArrayList<>();
        vehicleCollection.add(vehicle);
        vehicleCollection.add(vehicle1);
        userDetails.setVehicle(vehicleCollection);
        session.save(userDetails);
        session.save(vehicle);
        session.save(vehicle1);

        session.getTransaction().commit();

        session.close();
    }

    private static void createVehicleWithUser() {
        SessionFactory sessionFactory = com.trendcore.SessionFactory.getInstance().getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleName("Audi");
        UserDetails userDetails = getUserDetails();
        //vehicle.setUserDetails(userDetails);
        session.save(vehicle);
        session.save(userDetails);
        session.getTransaction().commit();
        session.close();
    }

    private static void createVehicle() {
        SessionFactory sessionFactory = com.trendcore.SessionFactory.getInstance().getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleName("Audi");
        session.save(vehicle);
        session.getTransaction().commit();
        session.close();
    }

    private static void fetchVehicle() {
        SessionFactory sessionFactory = com.trendcore.SessionFactory.getInstance().getSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Scanner scanner = new Scanner(System.in);
        Vehicle vehicle = (Vehicle) session.get(Vehicle.class, scanner.nextInt());

        session.close();

        //System.out.println(vehicle.getUserDetails());
    }

    private static void createUserWithVehicle() {
        SessionFactory sessionFactory = com.trendcore.SessionFactory.getInstance().getSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        UserDetails userDetails = getUserDetails();

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleName("Car");

        Collection<Vehicle> vehicleCollection = new ArrayList<>();
        vehicleCollection.add(vehicle);
        userDetails.setVehicle(vehicleCollection);
        session.save(userDetails);
        session.save(vehicle);

        session.getTransaction().commit();

        session.close();
    }

    private static void fetchUser() {
        SessionFactory sessionFactory = com.trendcore.SessionFactory.getInstance().getSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Scanner scanner = new Scanner(System.in);
        UserDetails userDetails = (UserDetails) session.get(UserDetails.class, scanner.nextInt());

        session.close();
        /**
         * In case of lazy fetch it will throw exception
         */
        System.out.println(userDetails.getUserId() + " " + userDetails.getUserName() + " " + userDetails.getListOfAddresses());
    }

    private static void createUser() {

        SessionFactory sessionFactory = com.trendcore.SessionFactory.getInstance().getSessionFactory();

        Scanner scanner = new Scanner(System.in);

        UserDetails userDetails = getUserDetails();
        //userDetails.setHomeAddress(getHomeAddress());

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(userDetails);
        session.getTransaction().commit();
        session.close();
    }

    private static UserDetails getUserDetails() {
        UserDetails userDetails = new UserDetails();
        userDetails.setUserName("Anurag");
        //userDetails.setAddress(getAddress());
        userDetails.setJoinedDate(new Date(System.currentTimeMillis()));
        userDetails.setDescription("Long String");

        userDetails.setTransientField("Test123");

        userDetails.getListOfAddresses().add(getAddress());
        userDetails.getListOfAddresses().add(getAddress());
        return userDetails;
    }

    private static HomeAddress getHomeAddress() {
        HomeAddress address = new HomeAddress();
        address.setStreet("Street");
        address.setCity("Pune");
        address.setCountry("India");
        return address;
    }

    private static Address getAddress() {
        Address address = new Address();
        address.setCity("Pune");
        address.setState("Maharashtra");
        address.setStreet("123 Street");
        address.setPincode("411030");
        return address;
    }


}
