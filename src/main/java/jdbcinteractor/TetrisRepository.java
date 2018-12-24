package jdbcinteractor;

import org.h2.tools.RunScript;
import org.pmw.tinylog.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public final class TetrisRepository {
    // JVD: dit is een voorbeeld! Puur illustratief
    private static final String SQL_POPULATE_DB = "CREATE TABLE IF NOT EXISTS tetrisblocks "
            + "(id int NOT NULL AUTO_INCREMENT, name VARCHAR(255), PRIMARY KEY ( id ))";
    private static final String SQL_ADD_BLOCK = "insert into tetrisblocks(name) values(?)";

    private TetrisRepository() {
    }

    // JVD: Illustratief: er zijn betere design patterns dan hier public static void te gebruiken...
    public static void populateDB() {
        System.out.println(new File(".").getAbsoluteFile());
        try (Statement stmt = JDBCInteractor.getConnection().createStatement()) {
            RunScript.execute(JDBCInteractor.getConnection(), new FileReader("src/main/resources/db/Tetris28.sql"));
            stmt.close();

        } catch (SQLException ex) {
            erroPopulating(ex);
            Logger.debug(ex.getStackTrace());
        } catch (FileNotFoundException ex) {
            erroPopulating(ex);
        }
    }

    private static void erroPopulating(Exception ex) {
        Logger.warn("Error populating db {}", ex.getLocalizedMessage());
    }

    // JVD: Illustratief, natuurlijk zal je hier een object moeten meegeven
    public static void addBlock() {
        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(SQL_ADD_BLOCK)) {
            prep.setString(1, "Mother of blocks");

            prep.execute();
        } catch (SQLException ex) {
            Logger.warn("Error adding block {}", ex.getLocalizedMessage());
            Logger.debug(ex.getStackTrace());
        }
    }
}
