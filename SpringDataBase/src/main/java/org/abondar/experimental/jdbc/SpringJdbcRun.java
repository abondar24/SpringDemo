package org.abondar.experimental.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by abondar on 07.07.16.
 */
public class SpringJdbcRun {

    private static Logger logger = LoggerFactory.getLogger(SpringJdbcRun.class);

    private static ContactDao contactDao;
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(JdbcConfiguration.class);
        ctx.refresh();

        contactDao = ctx.getBean(ContactDao.class);

        logger.info("List init contact data");
        listAllContacts();

        logger.info("Insert a new contact");
        Contact contact = new Contact();

        contact.setFirstName("Vasya");
        contact.setLastName("Pupkin");
        contact.setBirthDate(new Date(new GregorianCalendar(1975,14,1).getTime().getTime()));
        contactDao.insert(contact);

        logger.info("Listing after adding a new contact");
        listAllContacts();

        logger.info("Deleting a contact");
        contactDao.delete(contact.getId());

        logger.info("Listing after deleting");
        listAllContacts();

        logger.info("Name of the contact with id 1: "+contactDao.findFirstNameById(1L));
    }

    private static void listAllContacts(){
        List<Contact> contacts = contactDao.findAll();
        for (Contact contact:contacts){
            logger.info(contact.toString());
        }
    }
}
