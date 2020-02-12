package org.abondar.experimental.springdata.jpa;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by abondar on 18.07.16.
 */
public interface CarService {
    List<Car> findAll();

    Car save(Car car);

    void updateCarAgeJob();

    Car findByLicencePlate(String licencePlate);

    List<Car> findByAge(int age,int offset,int limit);

    String findCarData(int age, String licencePlate);
}
