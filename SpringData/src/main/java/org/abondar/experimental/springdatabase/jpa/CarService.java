package org.abondar.experimental.springdatabase.jpa;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by abondar on 18.07.16.
 */
@Service("carService")
public interface CarService {
    List<Car> findAll();

    Car save(Car car);

    void updateCarAgeJob();
}
