package org.abondar.experimental.springdata.jpa;

import org.abondar.experimental.springdata.jpa.car.Car;
import org.abondar.experimental.springdata.jpa.car.CarService;
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

        System.out.println(carService.findByLicencePlate("LICENSE-1005"));
        System.out.println(carService.findByAge(10,0,5));
    }

    private static void showCarList(CarService carService){
        List<Car> carList = carService.findAll();

        for (Car car : carList) {
            logger.info(car.toString());
        }
    }
}
