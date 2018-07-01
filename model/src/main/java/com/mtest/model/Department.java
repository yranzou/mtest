package com.mtest.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by yuri on 04.01.18.
 */
@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy="department")
    private Set<Employee> employees;

    //    @ManyToOne(targetEntity = Employee.class)
//    @JoinColumn(name = "chief_id")
    @Transient
    private int chiefId;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="chief_id")
    private Employee chief;

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

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Employee getChief() {
        return chief;
    }

    public void setChief(Employee chief) {
        this.chief = chief;
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
