package repo.util;

import java.sql.Connection;

import util.TetrisException;

public class MySQLConnection {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new TetrisException("Unable to load database driver", e);
        }
    }

    public static Connection getConnection() {
        return null; //DriverManager.getConnection(Credentials.URL, Credentials.UID, Credentials.PWD);
    }
}
