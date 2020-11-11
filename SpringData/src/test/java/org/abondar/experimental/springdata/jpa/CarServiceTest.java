package org.abondar.experimental.springdata.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    @Autowired
    private CarServiceImpl carService;


    @Test
    public void findByLicencePlate() {

        when(carRepository.findByLicencePlate("test")).thenReturn(Optional.of(new Car()));

        Car car = carService.findByLicencePlate("test");
        assertEquals(0, car.getId());
    }

    @Test
    public void findByAge() {
        when(carRepository.findAllByAgeAfter(10, PageRequest.of(0, 3)))
                .thenReturn(new PageImpl<>(Arrays.asList(new Car(), new Car(), new Car())));

        List<Car> carList = carService.findByAge(10, 0, 3);
        assertEquals(3, carList.size());
    }

    @Test
    public void findCarData() {
        Car co = new Car();
        co.setAge(10);
        co.setLicencePlate("test");

        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        CarView car = factory.createProjection(CarView.class, co);

        when(carRepository.findByAgeAndLicencePlate(10, "test"))
                .thenReturn(car);

        String res = carService.findCarData(10, "test");
        assertEquals("10-test", res);
    }
}
