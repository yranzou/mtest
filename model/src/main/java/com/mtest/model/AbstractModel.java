package com.mtest.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public AbstractModel() {
    }

    public AbstractModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
