package dk.cphbusiness;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBconnector {
    private static ConnectionPool singleton = new ConnectionPool();

    public static void setConnection( ConnectionPool con ) {
        singleton = con;
    }

    public static ConnectionPool getConnectionPool() {
        return singleton;
    }


}
