package org.abondar.experimental.springdata.mongo;

import org.joda.time.DateTime;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * Created by abondar on 18.07.16.
 */

@TypeAlias("car")
public class Car {


    @MongoId
    private long id;

    private String licencePlate;

    private String manufacturer;

    private DateTime manufactureDate;

    private int age;

    private int version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public DateTime getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(DateTime manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (age != car.age) return false;
        if (version != car.version) return false;
        if (licencePlate != null ? !licencePlate.equals(car.licencePlate) : car.licencePlate != null) return false;
        if (manufacturer != null ? !manufacturer.equals(car.manufacturer) : car.manufacturer != null) return false;
        return manufactureDate != null ? manufactureDate.equals(car.manufactureDate) : car.manufactureDate == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (licencePlate != null ? licencePlate.hashCode() : 0);
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (manufactureDate != null ? manufactureDate.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + version;
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", licencePlate='" + licencePlate + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", manufactureDate=" + manufactureDate +
                ", age=" + age +
                ", version=" + version +
                '}';
    }
}
