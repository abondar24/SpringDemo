package org.abondar.experimental.springdata.jdbc;

import java.util.List;

/**
 * Created by abondar on 06.07.16.
 */
public interface ContactDao {
    List<Contact> findAll();
    List<Contact> findAllWithDetail();
    List<Contact> findByFirstName(String firstName);
    String findLastNameById(Long id);
    String findFirstNameById(Long id);
    void insert(Contact contact);
    void insertWithDetail(Contact contact);
    void update (Contact contact);
    void delete(Long contactId);

}
