package com.mtest.model;

import java.sql.Blob;
import java.sql.Date;

/**
 *  Created by yuri on 26.11.17.
 */
public class Employee {

    private int id;
    private String name;
    private String surname;
    private String phone;
    private int chiefId;
    private int departmentId;
    private byte[] photo;
    private Date birthday;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

//    @Override
//    public String toString() {
//        StringBuilder builder = new StringBuilder();
//        builder.append("Employee [id=");
//        builder.append(id);
//        builder.append(", name=");
//        builder.append(name);
//        builder.append(", surname=");
//        builder.append(surname);
//        builder.append(", phone=");
//        builder.append(phone);
//        builder.append(", chief_id=");
//        builder.append(chiefId);
//        builder.append(", department_id=");
//        builder.append(departmentId);
//        builder.append("]");
//        return builder.toString();
//    }

}

