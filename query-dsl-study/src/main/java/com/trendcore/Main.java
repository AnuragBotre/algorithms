package com.trendcore;

import com.querydsl.core.support.QueryBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.trendcore.entity.QPerson;

import javax.persistence.EntityManager;

public class Main {

    public static void main(String[] args) {
        QPerson person = QPerson.person;

        JPAQuery query = new JPAQuery(null);
        QueryBase kent = query.from(person).where(person.firstname.eq("Kent"));


    }
}
