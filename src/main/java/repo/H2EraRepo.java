package repo;

import data.Era;
import repo.util.Strings;
import jdbcinteractor.JDBCInteractor;
import org.pmw.tinylog.Logger;
import util.TetrisException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class H2EraRepo implements EraRepo {

    private static final String FIELD_ERANAME = "eraname";
    private static final String FIELD_ERABACKGROUND = "erabackground";
    private static final String FIELD_ERAMUSIC = "eramusic";

    @Override
    public List<Era> getEras() {
        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(Strings.SELECT_ERAS)) {

            ResultSet rs = prep.executeQuery();
            List<Era> allEras = new ArrayList<>();

            while (rs.next()) {
                Era era = this.resultset2Era(rs);
                allEras.add(era);
            }

            prep.close();
            return allEras;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve all eras", ex);
        }
    }

    @Override
    public Era getEraById(int id) {
        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(Strings.SELECT_ERA_BY_ID)) {

            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            Era era = this.resultset2Era(rs);
            prep.close();
            return era;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve erabyid", ex);
        }

    }

    @Override
    public Era getEraByName(String name) {
        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(Strings.SELECT_ERA_BY_NAME)) {

            prep.setString(1, name);
            ResultSet rs = prep.executeQuery();
            Era era = this.resultset2Era(rs);
            prep.close();
            return era;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve all playerheroes", ex);
        }
    }

    @Override
    public void addEra(Era era) {
        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(Strings.ADD_ERA)) {
            prep.setString(1, era.getEraName());
            prep.setString(2, era.getEraBackground());
            prep.setString(3, era.getEraMusic());
            prep.executeUpdate();
        } catch (SQLException ex) {
            throw new TetrisException("Unable to add era into database", ex);
        }
    }

    @Override
    public void deleteEra(Era era) {
        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(Strings.DELETE_ERA)) {
            prep.setInt(1, era.getEraId());
            prep.executeUpdate();
        } catch (SQLException ex) {
            throw new TetrisException("Unable to delete era from database", ex);
        }
    }

    private Era resultset2Era(ResultSet rs) throws SQLException {
        String name = rs.getString(FIELD_ERANAME);
        String background = rs.getString(FIELD_ERABACKGROUND);
        String music = rs.getString(FIELD_ERAMUSIC);

        return new Era(name, background, music);
    }
}
