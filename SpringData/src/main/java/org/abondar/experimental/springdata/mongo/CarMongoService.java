package org.abondar.experimental.springdata.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarMongoService {

    private CarMongoRepository carMongoRepository;

    @Autowired
    public CarMongoService(CarMongoRepository carMongoRepository){
        this.carMongoRepository = carMongoRepository;
    }

    public void insert(Car car){
       carMongoRepository.insert(car);
    }

    public void updateAge(long id,int age){
        //uncomment for compile jpa and jdbc demo usage
       // Car car = carMongoRepository.findOne(id);
        Car car = carMongoRepository.findById(id);
        car.setAge(age);
        carMongoRepository.delete(car);
        carMongoRepository.save(car);

    }

    public List<Car> findCars(int offset, int limit){
        //uncomment for compile jpa and jdbc demo usage
        //Pageable pageable = new PageRequest(offset,limit);
        Pageable pageable = PageRequest.of(offset,limit);

        return carMongoRepository.findAll(pageable).getContent();

    }

    public Car findByLicencePlate(String plate){
        return carMongoRepository.findByLicencePlate(plate);
    }


    public void delete(Car car){
        carMongoRepository.delete(car);
    }

    public void clean(){
        carMongoRepository.deleteAll();
    }

}
