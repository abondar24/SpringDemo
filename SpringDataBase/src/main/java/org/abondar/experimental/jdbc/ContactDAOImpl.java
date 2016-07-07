package org.abondar.experimental.jdbc;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abondar on 07.07.16.
 */
public class ContactDAOImpl implements ContactDao, InitializingBean {


    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);

        MySQLErrorCodesTranslator errorCodesTranslator = new MySQLErrorCodesTranslator();
        errorCodesTranslator.setDataSource(dataSource);

        jdbcTemplate.setExceptionTranslator(errorCodesTranslator);

        this.jdbcTemplate = jdbcTemplate;

    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }

        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Contact> findAll() {
        Connection connection = null;
        List<Contact> result = new ArrayList<>();

        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from contact");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getLong("id"));
                contact.setFirstName(resultSet.getString("first_name"));
                contact.setLastName(resultSet.getString("last_name"));
                contact.setBirthDate(resultSet.getDate("birth_date"));
                result.add(contact);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public List<Contact> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public String findLastNameById(Long id) {
        return null;
    }

    @Override
    public String findFirstNameById(Long id) {

        return jdbcTemplate.queryForObject("select first_name from contact where id = ?",
                new Object[]{id},String.class);
    }

    @Override
    public void insert(Contact contact) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection
                    .prepareStatement("insert into contact (first_name,last_name,birth_date) values (?,?,?)");
            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.setDate(3, contact.getBirthDate());
            statement.execute();

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                contact.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public void update(Contact contact) {

    }

    @Override
    public void delete(Long contactId) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement statement = connection
                    .prepareStatement("delete from contact where id=?");
            statement.setLong(1, contactId);
            statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null) {
            throw new BeanCreationException("Set dataSource to ContactDAO");
        }

        if (jdbcTemplate == null){
            throw  new BeanCreationException("Null JdbcTemplate on ContactDAO");
        }
    }
}
