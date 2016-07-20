package org.abondar.experimental.springrest.jmx;

import org.abondar.experimental.springdatabase.jpa.CarService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by abondar on 20.07.16.
 */
public class AppStatsImpl implements AppStats{

    @Autowired
    private CarService carService;

    @Override
    public int getTotalCarCount() {
        return carService.findAll().size();
    }
}
