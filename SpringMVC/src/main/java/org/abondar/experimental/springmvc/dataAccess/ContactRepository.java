package org.abondar.experimental.springmvc.dataAccess;

import org.abondar.experimental.springmvc.model.Contact;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by abondar on 20.07.16.
 */

public interface ContactRepository extends CrudRepository<Contact,Long>{
}
