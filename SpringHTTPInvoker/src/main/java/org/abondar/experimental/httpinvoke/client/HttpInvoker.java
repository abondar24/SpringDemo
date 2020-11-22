package org.abondar.experimental.httpinvoke.client;

import org.abondar.experimental.springdata.jdbc.Contact;
import org.abondar.experimental.springdata.jdbc.ContactDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by abondar on 19.07.16.
 */
public class HttpInvoker {
    private static final Logger logger = LoggerFactory.getLogger(HttpInvoker.class);

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
