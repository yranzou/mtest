package com.mtest.model;

import javax.persistence.*;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "phone")
public class Phone implements Comparable<Phone>{
//    private Map<Integer,PhoneType> phones;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "number")
    private String number;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PhoneType type;

//    @OneToMany(targetEntity = Employee.class)
//    private Set<Employee> employees;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public Set<Employee> getEmployees() {
//        return employees;
//    }
//
//    public void setEmployees(Set<Employee> employees) {
//        this.employees = employees;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;
        Phone phone = (Phone) o;
        return getNumber() == phone.getNumber();
    }

    @Override
    public int compareTo(Phone o) {
        return number.compareTo(o.getNumber());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getNumber());
    }
}
