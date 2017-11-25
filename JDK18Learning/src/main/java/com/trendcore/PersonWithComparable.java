package com.trendcore;

/**
 * Created by Anurag
 */
public class PersonWithComparable extends Person implements Comparable<PersonWithComparable> {

    public PersonWithComparable(String name, int age){
        super(name,age);
    }

    @Override
    public int compareTo(PersonWithComparable otherPerson) {
        //return this.getName().compareTo(o.getName());
        return this.getAge() < otherPerson.getAge() ? -1 :
                this.getAge() == otherPerson.getAge() ? 0 : 1;
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getAge();
    }
}
