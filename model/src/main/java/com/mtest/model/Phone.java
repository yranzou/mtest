package com.mtest.model;

import java.util.Map;
import java.util.Objects;

public class Phone implements Comparable<Phone>{
//    private Map<Integer,PhoneType> phones;
    private String number;
    private PhoneType type;

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
