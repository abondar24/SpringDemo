package org.abondar.experimental.graphclient.model;

import java.util.Random;

public class Employee {

    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private Department department;

    public Employee(){}

    public Employee(String firstName, String lastName, String phone) {
        Random rand = new Random();
        this.id = rand.nextInt();

        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", department=" + department +
                '}';
    }
}
