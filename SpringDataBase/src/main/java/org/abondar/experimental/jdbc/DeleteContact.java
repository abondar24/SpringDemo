package org.abondar.experimental.jdbc;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by abondar on 11.07.16.
 */
public class DeleteContact extends SqlUpdate {
    private static final String SQL_DELETE_CONTACT = "delete from contact where id = :id";


    public DeleteContact(DataSource dataSource){
        super(dataSource,SQL_DELETE_CONTACT);

        super.declareParameter(new SqlParameter("id", Types.INTEGER));

    }
}
