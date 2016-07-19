package org.abondar.experimental.httpinvoke;

import org.abondar.experimental.springdatabase.jdbc.Contact;
import org.abondar.experimental.springdatabase.jdbc.ContactDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by abondar on 19.07.16.
 */
public class HttpInvokerRun {
    private static Logger logger = LoggerFactory.getLogger(HttpInvokerRun.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(InvokerClientConfig.class);
        ctx.refresh();

        ContactDao contactDao = ctx.getBean(ContactDao.class);
        logger.info("Finding contacts");
        List<Contact> contacts = contactDao.findAll();
        listContacts(contacts);

        logger.info("Finding contact Chris");
        contacts = contactDao.findByFirstName("Chris");
        listContacts(contacts);
    }

    private static void listContacts(List<Contact>contacts){
        for (Contact contact:contacts){
            logger.info(contact.toString());
        }
    }
}
