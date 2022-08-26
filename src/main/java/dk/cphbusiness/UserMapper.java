package dk.cphbusiness;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMapper {

    ConnectionPool connectionPool;

    public UserMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    public List<String> findAllNames() {
        List<String> names = new ArrayList<>();
        try (Connection conn = connectionPool.getConnection()) {
            String sql = "SELECT fname FROM usertable";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                names.add(rs.getString("fname"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }

    public Optional<User> findById(int i) {
        try (Connection conn = connectionPool.getConnection()) {
            String sql = "SELECT * FROM usertable WHERE id = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, i);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                String password = rs.getString(4);
                String phone = rs.getString(5);
                String address = rs.getString(6);

                User user = new User(id, fname, lname, password, phone, address);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean updateDetails(User user) {
        try (Connection conn = connectionPool.getConnection()) {
            String sql = "UPDATE usertable SET fname = ?, lname = ?, pw = ?, `phone` = ?, `address` =? WHERE `id` = ?;";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, user.getFname());
            pstm.setString(2, user.getLname());
            pstm.setString(3, user.getPassword());
            pstm.setString(4, user.getPhone());
            pstm.setString(5, user.getAddress());
            pstm.setInt(6, user.getId());

            int status = pstm.executeUpdate();

            return status == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
