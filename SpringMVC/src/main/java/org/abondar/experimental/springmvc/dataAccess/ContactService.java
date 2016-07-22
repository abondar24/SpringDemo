package org.abondar.experimental.springmvc.dataAccess;

import org.abondar.experimental.springmvc.model.Contact;

import java.util.List;

/**
 * Created by abondar on 20.07.16.
 */
public interface ContactService {
    List<Contact> findAll();
    Contact findById(Long id);
    Contact save(Contact contact);
}
