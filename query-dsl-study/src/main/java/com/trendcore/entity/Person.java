package com.trendcore.entity;

import com.querydsl.sql.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(value = "firstname")
    private String firstname;

    @Column(value = "surname")
    private String surname;

    Person() {
    }

    public Person(String firstname, String surname) {
        this.firstname = firstname;
        this.surname = surname;
    }

}
