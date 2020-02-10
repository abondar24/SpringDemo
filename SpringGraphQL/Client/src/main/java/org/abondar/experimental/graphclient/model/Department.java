package org.abondar.experimental.graphclient.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Department {
    private Integer id;
    private String name;
    private List<Employee> employees;


    public Department(){}

    public Department(String name) {
        Random rand = new Random();
        this.id = rand.nextInt();

        this.name = name;
        this.employees = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
