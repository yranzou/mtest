package com.mtest.model;

/**
 * Created by yuri on 04.01.18.
 */
public class Department {
    private int id;
    private String name;
    private String chief_id;

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

    public String getChief_id() {
        return chief_id;
    }

    public void setChief_id(String chief_id) {
        this.chief_id = chief_id;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Department [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", chief_id=");
        builder.append(chief_id);
        builder.append("]");
        return builder.toString();
    }
}
