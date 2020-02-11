package org.abondar.experimental.springdata.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by abondar on 07.07.16.
 */
public class JdbcRun {

    private static Logger logger = LoggerFactory.getLogger(JdbcRun.class);

    private static ContactDao contactDao;
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(JdbcConfiguration.class);
        ctx.refresh();

        contactDao = ctx.getBean(ContactDao.class);

        List<Contact> contacts = contactDao.findAll();

        logger.info("List init contact data");
        listAll(contacts);

        logger.info("Insert a new contact");
        Contact contact = new Contact();

        contact.setFirstName("Vasya");
        contact.setLastName("Pupkin");
        contact.setBirthDate(new Date(new GregorianCalendar(1975,14,1).getTime().getTime()));
        contactDao.insert(contact);

        logger.info("Listing after adding a new contact");
        List<Contact> contacts1 = contactDao.findAll();
        listAll(contacts1);

        logger.info("Deleting a contact");
        contactDao.delete(contact.getId());

        logger.info("Listing after deleting");
        List<Contact> contacts2 = contactDao.findAll();
        listAll(contacts2);

        logger.info("First name of the contact with id 1: "+contactDao.findFirstNameById(1L));
        logger.info("Last name of the contact with id 1: "+contactDao.findLastNameById(1L));

        logger.info("Listing with details");
        List<Contact> contactsWithDetails = contactDao.findAllWithDetail();
        listAll(contactsWithDetails);


        logger.info("Contact with first name John: "+contactDao.findByFirstName("John"));

        Contact contactUPD = contactsWithDetails.get(1);
        contactUPD.setLastName("Daniels");
        contactDao.update(contactUPD);


        Contact contactDet = new Contact();
        contactDet.setFirstName("Michael");
        contactDet.setLastName("Jackson");
        contactDet.setBirthDate(new Date((new GregorianCalendar(1964, 10, 1)).getTime().getTime()));

        List<ContactTelDetail> contactTelDetails = new ArrayList<>();

        ContactTelDetail contactTelDetail = new ContactTelDetail();
        contactTelDetail.setTelType("Home");
        contactTelDetail.setTelNumber("11111111");

        contactTelDetails.add(contactTelDetail);

        contactTelDetail = new ContactTelDetail();
        contactTelDetail.setTelType("Mobile");
        contactTelDetail.setTelNumber("22222222");

        contactTelDetails.add(contactTelDetail);

        contactDet.setContactTelDetails(contactTelDetails);

        contactDao.insertWithDetail(contactDet);

        logger.info("Listing with details after insert");
        List<Contact> contactsWithDetails1 = contactDao.findAllWithDetail();
        listAll(contactsWithDetails1);
    }

    private static void listAll(List<Contact> contacts){

        for (Contact contact:contacts){


            if (contact.getContactTelDetails() !=null){
                for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()){
                    logger.info(contact.toString()+ " "+ contactTelDetail);
                }
            } else {
                logger.info(contact.toString());
            }
        }
    }
}
