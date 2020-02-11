package org.abondar.experimental.springdata.jpa.car;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by abondar on 18.07.16.
 */
@Repository
public interface CarRepository extends CrudRepository<Car,Long>{

  Optional<Car> findByLicencePlate(String licencePlate);
  Page<Car> findAllByAgeAfter(int age, Pageable pageable);
}
