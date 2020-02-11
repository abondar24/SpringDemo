package org.abondar.experimental.springrest;

import org.abondar.experimental.springdata.jpa.car.Car;
import org.abondar.experimental.springdata.jpa.car.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by abondar on 20.07.16.
 */
@RestController
@RequestMapping(value = "/cars")
public class CarController {
    private Logger logger = LoggerFactory.getLogger(CarController.class);

     @Autowired
     private CarService carService;


    @RequestMapping(value = "/listData", method = RequestMethod.GET)
    @ResponseBody
    public List<Car> listData() {
        return carService.findAll();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Car save(@RequestBody Car car) {
        logger.info("Saving a new car: " + car);
        carService.save(car);
        logger.info("Saved a new car");

        return car;
    }

    @RequestMapping(value = "/updateAges", method = RequestMethod.PUT)
    @ResponseBody
    public void update() {
        logger.info("UpdatingAges");
        carService.updateCarAgeJob();

    }
}
