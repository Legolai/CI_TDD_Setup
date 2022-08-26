package dk.cphbusiness;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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


        } catch (SQLException e){
            e.printStackTrace();
        }
       return names;
    }
}
