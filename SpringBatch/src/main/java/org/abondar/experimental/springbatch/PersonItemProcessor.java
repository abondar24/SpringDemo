package org.abondar.experimental.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by abondar on 25.07.16.
 */
public class PersonItemProcessor implements ItemProcessor<Person,Person> {

    private static Logger logger = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public Person process(Person person) throws Exception {
        String firstName = person.getFirstName().toUpperCase();
        String lastName = person.getLastName().toUpperCase();

        Person tranformedPerson = new Person();
        tranformedPerson.setFirstName(firstName);
        tranformedPerson.setLastName(lastName);
        return tranformedPerson;
    }
}
