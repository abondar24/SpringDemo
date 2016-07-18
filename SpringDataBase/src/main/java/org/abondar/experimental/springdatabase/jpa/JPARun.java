package org.abondar.experimental.springdatabase.jpa;

import org.abondar.experimental.springdatabase.jdbc.JdbcRun;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by abondar on 18.07.16.
 */
public class JPARun {
    private static Logger logger = LoggerFactory.getLogger(JPARun.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(JPAConfig.class);
        ctx.refresh();

        CarService carService = ctx.getBean("carService", CarService.class);
        showCarList(carService);

        Car car = new Car();
        car.setAge(12);
        car.setLicencePlate("S217PB98RUS");
        car.setManufactureDate(new DateTime().withDate(2004,9,24));
        car.setManufacturer("LADA");

        carService.save(car);
        logger.info("Saved a new car");
        showCarList(carService);

        carService.updateCarAgeJob();
        showCarList(carService);

    }

    private static void showCarList(CarService carService){
        List<Car> carList = carService.findAll();

        for (Car car : carList) {
            logger.info(car.toString());
        }
    }
}
