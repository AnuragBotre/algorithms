package com.trendcore.entities;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Anurag
 */
@Entity(name = "USER_DETAILS")
public class UserDetails {

    /**
     * This annotation is used if the object is primary key from other class.
     * Tutorial 09 will give more insight
     */
    //@EmbeddedId

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "JOINED_DATE")
    private Date joinedDate;

    /*@Embedded
    private Address address;

    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "HOME_STREET")),
            @AttributeOverride(name = "city", column = @Column(name = "HOME_CITY")),
            @AttributeOverride(name = "country", column = @Column(name = "HOME_COUNTRY")),
    })
    @Embedded
    private HomeAddress homeAddress;*/

    /**
     * Instead of using specific type Like hashset use collection
     */
    /*@ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ADDRESS",joinColumns = @JoinColumn(name = "USER_ID"))
    private Set<Address> listOfAddresses = new HashSet<>();*/

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ADDRESS", joinColumns = @JoinColumn(name = "USER_ID"))
    @GenericGenerator(name = "hilo-gen", strategy = "hilo")
    @CollectionId(columns = {@Column(name = "ADDRESS_ID")}, generator = "hilo-gen", type = @Type(type = "long"))
    private Collection<Address> listOfAddresses = new ArrayList<>();

    /**
     * This is one to one mapping
     */
    /*@OneToOne
    @JoinColumn(name = "VEHICLE_ID")
    private Vehicle vehicle;*/

    /*@OneToMany
    @JoinTable(name = "USER_VEHICLE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "VEHICLE_ID")
    )*/
    @OneToMany(mappedBy = "userDetails")
    private Collection<Vehicle> vehicle;

    @Transient
    private String transientField;

    @Lob
    private String description;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    /*public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }*/

    public String getTransientField() {
        return transientField;
    }

    public void setTransientField(String transientField) {
        this.transientField = transientField;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public HomeAddress getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(HomeAddress homeAddress) {
        this.homeAddress = homeAddress;
    }*/

    public Collection<Address> getListOfAddresses() {
        return listOfAddresses;
    }

    public void setListOfAddresses(Collection<Address> listOfAddresses) {
        this.listOfAddresses = listOfAddresses;
    }

    /*public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }*/

    public Collection<Vehicle> getVehicle() {
        return vehicle;
    }

    public void setVehicle(Collection<Vehicle> vehicle) {
        this.vehicle = vehicle;
    }
}
