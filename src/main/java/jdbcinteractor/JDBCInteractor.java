package jdbcinteractor;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.jdbc.JDBCClient;
import org.h2.tools.Server;
import org.pmw.tinylog.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCInteractor extends AbstractVerticle {

    private Server dbServer;
    private Server webDB;
    private JDBCClient jdbcClient;

    static final String DB_URL = "jdbc:h2:~/tetris-28;MODE=MySQL";
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String USER = "sa";
    static final String PASS = "";


    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(JDBC_DRIVER);

            Logger.info("Connecting to database...");
            con = DriverManager.getConnection(DB_URL, USER, PASS);


        } catch (SQLException ex) {
            Logger.warn("Error connecting to the database: {}", ex.getLocalizedMessage());
            Logger.debug(ex.getStackTrace());
        } catch (ClassNotFoundException e) {
            Logger.warn("Error connecting to db: {}", e.getLocalizedMessage());
            Logger.debug(e.getStackTrace());
        }
        return con;
    }

    public void startDBServer() {

        try {
            // start de DB
            Server.createTcpServer().start();

            // start een web interface
            // [RULE]: Web client poort MOET 90xx zijn (xx is groepsnummer met leading zero)
            Server.createWebServer("-webPort", "9028").start();
        } catch (SQLException e) {
            Logger.warn("Error starting database: {}", e.getLocalizedMessage());
            Logger.debug(e.getStackTrace());
        }
    }
}
