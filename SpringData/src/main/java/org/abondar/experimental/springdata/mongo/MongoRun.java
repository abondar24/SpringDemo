package org.abondar.experimental.springdata.mongo;


import org.joda.time.DateTime;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MongoRun {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(MongoConfig.class);
        ctx.refresh();

        CarMongoService carService = ctx.getBean("carMongoService", CarMongoService.class);

        carService.clean();

        String licencePlate = "S217PB98RUS";
        Car car = new Car();
        car.setAge(12);
        car.setLicencePlate(licencePlate);
        car.setManufactureDate(new DateTime().withDate(2004,9,24));
        car.setManufacturer("LADA");
        carService.insert(car);

        Car saved = carService.findByLicencePlate(licencePlate);
        System.out.println("Found car: "+saved);

        carService.updateAge(saved.getId(),16);
        saved = carService.findByLicencePlate(licencePlate);
        System.out.println("Updated licence: "+ saved);

        Car car1 = new Car();
        car1.setId(1);
        car1.setAge(12);
        car1.setLicencePlate("S101PB98RUS");
        car1.setManufactureDate(new DateTime().withDate(2008,1,27));
        car1.setManufacturer("BMW");
        carService.insert(car1);

        List<Car> carList = carService.findCars(0,3);
        System.out.println("Found cars: "+carList);

        carService.delete(car);
        carList = carService.findCars(0,3);
        System.out.println("Found cars: "+carList);


    }
}
