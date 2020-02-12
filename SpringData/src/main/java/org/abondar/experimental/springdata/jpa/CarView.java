package org.abondar.experimental.springdata.jpa;

import org.springframework.beans.factory.annotation.Value;



public interface CarView {

    int getAge();

    String getLicencePlate();

    @Value("#{target.age}-#{target.licencePlate}")
    String getCarData();
}
