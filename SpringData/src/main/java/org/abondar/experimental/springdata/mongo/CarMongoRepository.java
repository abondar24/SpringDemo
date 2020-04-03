package org.abondar.experimental.springdata.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarMongoRepository extends MongoRepository<Car, Long> {

    Car findByLicencePlate(String plate);

    Car findById(long id);

}
