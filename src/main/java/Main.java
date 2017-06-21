import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by guzik on 5/30/17.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("aaaaaaaaaaaaaa");

        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriver(new com.mysql.jdbc.Driver());
        dataSource.setUrl("jdbc:mysql://mn19.webd.pl/krguznic_JacksDay");
        dataSource.setUsername("krguznic_JacksDa");
        dataSource.setPassword("$nufk!n");

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);

        String sqlInsert = "INSERT INTO contact (name, email, address, telephone)"
                + " VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sqlInsert, "Tom", "tomea@mail.com", "USA", "12345");

        String sqlUpdate = "UPDATE contact set email=? where name=?";
        jdbcTemplate.update(sqlUpdate, "tomee@mail.com", "Tom");

        String sqlSelect = "SELECT * FROM contact";
     //   String sqlSelect = "SELECT * FROM Customers";
        List<Contact> listContact = jdbcTemplate.query(sqlSelect, new RowMapper<Contact>() {

            public Contact mapRow(ResultSet result, int rowNum) throws SQLException {
                Contact contact = new Contact();
                contact.setName(result.getString("name"));
                contact.setEmail(result.getString("email"));
                contact.setAddress(result.getString("address"));
                contact.setPhone(result.getString("telephone"));

                return contact;
            }

        });

        for (Contact aContact : listContact) {
            System.out.println(aContact);
        }

 //       String sqlDelete = "DELETE FROM contact1 where name=?";
 //       jdbcTemplate.update(sqlDelete, "Tom");
    }
}
