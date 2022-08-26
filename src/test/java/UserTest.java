import dk.cphbusiness.ConnectionPool;
import dk.cphbusiness.DBconnector;
import dk.cphbusiness.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @BeforeEach
    void setUp() {
        System.out.println("TESTINNNNGGGG");
        ConnectionPool cp = DBconnector.getConnectionPool();
        try (Connection con = cp.getConnection())  {;
            String dropTable = "DROP TABLE IF EXISTS `startcode_test`.`usertable`";
            con.prepareStatement(dropTable).executeUpdate();
            String createTable = "CREATE TABLE IF NOT EXISTS `startcode_test`.`usertable` (\n" + "  `id` INT NOT NULL AUTO_INCREMENT,\n" + "  `fname` VARCHAR(45) NULL,\n" + "  `lname` VARCHAR(45) NULL,\n" + "  `pw` VARCHAR(45) NULL,\n" + "  `phone` VARCHAR(45) NULL,\n" + "  `address` VARCHAR(45) NULL,\n" + "  PRIMARY KEY (`id`));";
            con.prepareStatement(createTable).executeUpdate();
            String SQL = "INSERT INTO startcode_test.usertable (fname, lname, pw, phone, address) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "Hans");
            ps.setString(2, "Hansen");
            ps.setString(3, "Hemmelig123");
            ps.setString(4, "40404040");
            ps.setString(5, "Rolighedsvej 3");
            ps.executeUpdate();

            ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "Peter");
            ps.setString(2, "Jensen");
            ps.setString(3, "Password123");
            ps.setString(4, "88888888");
            ps.setString(5, "Rolighedsvej 13");
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getUsersTest() {
        System.out.println("Testing getting names of all users");
        UserMapper um = new UserMapper(DBconnector.getConnectionPool());

        List<String> actual = um.findAllNames();
        List<String> expected = new ArrayList<>(List.of("Hans", "Peter"));

        assertEquals(expected, actual);
    }
}
