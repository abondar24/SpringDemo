package org.abondar.experimental.springdatabase.jpa;

import com.google.inject.internal.util.Lists;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by abondar on 18.07.16.
 */
@Service("carService")
@Repository
@Transactional
public class CarServiceImpl implements CarService{
    private Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    CarRepository carRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Car> findAll() {
        return Lists.newArrayList(carRepository.findAll());
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void updateCarAgeJob() {
      List<Car> cars = findAll();
        DateTime curTime = DateTime.now();
        logger.info("Car age update started");

        for (Car car : cars){
            int age = Years.yearsBetween(car.getManufactureDate(),curTime).getYears();
            car.setAge(age);
            save(car);
            logger.info("Car age update---"+car);
        }
        logger.info("Car age update completed");
    }
}
