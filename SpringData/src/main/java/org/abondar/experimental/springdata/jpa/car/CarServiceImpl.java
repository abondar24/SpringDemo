package org.abondar.experimental.springdata.jpa.car;

import com.google.inject.internal.util.Lists;

import org.joda.time.DateTime;
import org.joda.time.Years;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

/**
 * Created by abondar on 18.07.16.
 */
@Service("carService")

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
    @Scheduled(cron="0 2 * * * *") //for running in task module
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

    @Override
    public Car findByLicencePlate(String licencePlate) {
        Optional<Car> car =carRepository.findByLicencePlate(licencePlate);
        return car.orElse(new Car());
    }

    @Override
    public List<Car> findByAge(int age, int offset,int limit) {
        Pageable pageable = new PageRequest(offset,limit).next();

        return carRepository
                .findAllByAgeAfter(age,pageable)
                .getContent();
    }


}
