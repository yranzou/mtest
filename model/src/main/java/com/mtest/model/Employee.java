package com.mtest.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *  Created by yuri on 26.11.17.
 */

@Entity
@Table(name = "employee")
public class Employee {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    @Transient
    private int chiefId;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="chief_id")
    @JsonBackReference
    private Employee chief;

    @OneToMany(mappedBy="chief", fetch = FetchType.EAGER)
    private Set<Employee> subordinates = new HashSet<>();

    //    @ManyToOne(targetEntity = Department.class)
//    @JoinColumn(name = "department_id")
    @Transient
    private int departmentId;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="department_id")
    @JsonBackReference
    private Department department;

    @Lob
    @Column(name = "photo")
    @JsonIgnore
    private byte[] photo;

    @Column(name = "birthdate")
    private Date birthday;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "employee_phone",
        joinColumns = { @JoinColumn(name = "employee_id") },
        inverseJoinColumns = { @JoinColumn(name = "phone_id") })
    @ElementCollection(targetClass=Phone.class)
    @JsonManagedReference
    private Set<Phone> phones = new HashSet<>(0);

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
    public Set<Employee> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(Set<Employee> subordinates) {
        this.subordinates = subordinates;
    }


    public Employee getChief() {
        return chief;
    }

    public void setChief(Employee chief) {
        this.chief = chief;
    }

    public int getChiefId() {
        return chiefId;
    }

    public void setChiefId(int chiefId) {
        this.chiefId = chiefId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Employee [id=");
        builder.append(getId());
        builder.append(", name=");
        builder.append(name);
        builder.append(", surname=");
        builder.append(surname);
        builder.append(", phone=");
//        builder.append(phone);
        builder.append(", chief_id=");
        builder.append(chiefId);
        builder.append(", department_id=");
        builder.append(departmentId);
        builder.append("]");
        return builder.toString();
    }

}

