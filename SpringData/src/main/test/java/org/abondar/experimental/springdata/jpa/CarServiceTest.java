package org.abondar.experimental.springdata.jpa;

import org.abondar.experimental.springdata.jpa.car.Car;
import org.abondar.experimental.springdata.jpa.car.CarRepository;
import org.abondar.experimental.springdata.jpa.car.CarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    private CarService carService;

    @Before
    public void setUp(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(JPAConfig.class);
        ctx.refresh();

        carService = ctx.getBean("carService", CarService.class);

    }

    @Test
    public void findByLicencePlate() {

        when(carRepository.findByLicencePlate("test")).thenReturn(Optional.of(new Car()));

        Car car = carService.findByLicencePlate("test");
        assertEquals(0,car.getId());
    }
}
