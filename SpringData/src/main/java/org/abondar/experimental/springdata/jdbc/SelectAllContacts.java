package org.abondar.experimental.springdata.jdbc;

import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by abondar on 10.07.16.
 */
public class SelectAllContacts extends MappingSqlQuery<Contact> {

        private static final String SQL_SELECT_ALL_CONTACTS = "select id,first_name,last_name,birth_date from contact";

        public SelectAllContacts(DataSource ds){
        super(ds,SQL_SELECT_ALL_CONTACTS);
        }

    @Override
    protected Contact mapRow(ResultSet resultSet, int i) throws SQLException {
        Contact contact = new Contact();
        contact.setId(resultSet.getLong("id"));
        contact.setFirstName(resultSet.getString("first_name"));
        contact.setLastName(resultSet.getString("last_name"));
        contact.setBirthDate(resultSet.getDate("birth_date"));

        return contact;
    }
}
