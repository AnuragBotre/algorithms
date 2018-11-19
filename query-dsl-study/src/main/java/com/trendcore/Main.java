package com.trendcore;

import com.querydsl.core.alias.Alias;
import com.querydsl.core.support.QueryBase;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.jpa.OpenJPATemplates;
import com.querydsl.jpa.impl.JPAQuery;
import com.trendcore.entity.Person;
import com.trendcore.entity.QPerson;

import javax.persistence.EntityManager;

public class Main {

    public static void main(String[] args) {
        QPerson person = QPerson.person;

        JPAQuery query = new JPAQuery();
        QueryBase kent = query.from(person).where(
                person.firstname.eq("Kent")
                        .and(person.surname.eq("beck")),
                person.surname.eq("test")
            );

        System.out.println(kent.toString());

        QPerson qPerson = new QPerson("test");
        query = new JPAQuery();
        query.from(qPerson);
        System.out.println(query.toString());
    }
}
