package org.abondar.experimental.springdata.jdbc;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by abondar on 11.07.16.
 */
public class InsertContact extends SqlUpdate {
    private static final String SQL_INSERT_CONTACT = "insert into contact (first_name,last_name,birth_date)" +
            "values (:first_name, :last_name, :birth_date )";


    public InsertContact(DataSource dataSource){
        super(dataSource,SQL_INSERT_CONTACT);
        super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("last_name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("birth_date", Types.DATE));
        super.setGeneratedKeysColumnNames("id");
        super.setReturnGeneratedKeys(true);

    }
}
