package com.trendcore.entities;

import javax.persistence.*;

/**
 * Created by Anurag
 */
@Entity
public class Vehicle {

    @Id
    @GeneratedValue
    @Column(name = "VEHICLE_ID")
    private int vehicleId;

    @Column
    private String vehicleName;

    /*@OneToOne
    private UserDetails userDetails;*/

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserDetails userDetails;

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    /*public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }*/
}
