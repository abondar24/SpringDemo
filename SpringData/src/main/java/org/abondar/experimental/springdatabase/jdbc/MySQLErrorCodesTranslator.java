package org.abondar.experimental.springdatabase.jdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import java.sql.SQLException;

/**
 * Created by abondar on 07.07.16.
 */
public class MySQLErrorCodesTranslator extends SQLErrorCodeSQLExceptionTranslator{

    @Override
    protected DataAccessException customTranslate(String task, String sql, SQLException sqlex){
        if (sqlex.getErrorCode() == -12345){
            return new DeadlockLoserDataAccessException(task,sqlex);
        }
        return null;
    }


}
