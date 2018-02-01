package com.mtest.model;

/**
 * Created by yuri on 04.01.18.
 */
public class Department {
    private int id;
    private String name;
    private int chiefId;

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

    public int getChiefId() {
        return chiefId;
    }

    public void setChiefId(int chiefId) {
        this.chiefId = chiefId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Department [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", chief_id=");
        builder.append(chiefId);
        builder.append("]");
        return builder.toString();
    }
}
