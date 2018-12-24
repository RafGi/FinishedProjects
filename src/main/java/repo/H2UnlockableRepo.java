package repo;

import data.Player;
import data.Unlockable;
import jdbcinteractor.JDBCInteractor;
import repo.util.Strings;
import org.pmw.tinylog.Logger;
import util.TetrisException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class H2UnlockableRepo implements UnlockableRepo {

    private static final String FIELD_UNLOCKABLEID = "unlockableid";
    private static final String FIELD_UNLOCKABLETYPE = "unlockabletype";
    private static final String FIELD_UNLOCKABLENAME = "unlockablename";
    private static final String FIELD_UNLOCKABLEMEDIA = "unlockablemedia";
    private static final String FIELD_UNLOCKABLECOST = "unlockablecost";

    @Override
    public List<Unlockable> getUnlockables() {
        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(Strings.SELECT_UNLOCKABLES)) {

            ResultSet rs = prep.executeQuery();
            List<Unlockable> allUnlockables = new ArrayList<>();

            while (rs.next()) {
                Unlockable unlockable = this.resultset2Unlockable(rs);
                allUnlockables.add(unlockable);
            }

            prep.close();
            return allUnlockables;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve all unlockables", ex);
        }
    }

    @Override
    public List<Unlockable> getUnlockableByType(int type) {
        try (PreparedStatement prep = JDBCInteractor.getConnection()
                .prepareStatement(Strings.SELECT_UNLOCKABLES_BY_TYPE)) {

            prep.setInt(1, type);
            ResultSet rs = prep.executeQuery();
            List<Unlockable> allUnlockablesByType = new ArrayList<>();

            while (rs.next()) {
                Unlockable unlockable = this.resultset2Unlockable(rs);
                allUnlockablesByType.add(unlockable);
            }

            prep.close();
            return allUnlockablesByType;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve allUnlockablesByType", ex);
        }
    }

    @Override
    public Unlockable getUnlockableById(int id) {
        try (PreparedStatement prep = JDBCInteractor.getConnection()
                .prepareStatement(Strings.SELECT_UNLOCKABLES_BY_ID)) {

            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            Unlockable unlockable = this.resultset2Unlockable(rs);
            prep.close();
            return unlockable;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve unlockablebyid", ex);
        }

    }

    @Override
    public List<Unlockable> getUnlockableByPlayer(Player player) {
        try (PreparedStatement prep = JDBCInteractor.getConnection()
                .prepareStatement(Strings.SELECT_UNLOCKABLES_BY_PLAYER)) {

            prep.setInt(1, player.getId());
            ResultSet rs = prep.executeQuery();
            List<Unlockable> playerUnlockables = new ArrayList<>();

            while (rs.next()) {
                Unlockable hero = this.resultset2Unlockable(rs);
                playerUnlockables.add(hero);
            }

            prep.close();
            return playerUnlockables;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve all playerunlockables", ex);
        }
    }

    @Override
    public void addUnlockable(Unlockable unlockable) {
        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(Strings.ADD_UNLOCKABLE)) {
            prep.setInt(1, unlockable.getType());
            prep.setString(1, unlockable.getName());
            prep.setString(2, unlockable.getLinkToMedia());
            prep.setDouble(3, unlockable.getCost());
            prep.executeUpdate();
        } catch (SQLException ex) {
            throw new TetrisException("Unable to add unlockable into database", ex);
        }

    }

    @Override
    public void deleteUnlockable(Unlockable unlockable) {
        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(Strings.DELETE_UNLOCKABLE)) {
            prep.setInt(1, unlockable.getId());
            prep.executeUpdate();
        } catch (SQLException ex) {
            throw new TetrisException("Unable to delete era from database", ex);
        }
    }

    private Unlockable resultset2Unlockable(ResultSet rs) throws SQLException {
        int id = rs.getInt(FIELD_UNLOCKABLEID);
        String name = rs.getString(FIELD_UNLOCKABLENAME);
        int type = rs.getInt(FIELD_UNLOCKABLETYPE);
        String linkToMedia = rs.getString(FIELD_UNLOCKABLEMEDIA);
        int cost = rs.getInt(FIELD_UNLOCKABLECOST);

        return new Unlockable(id, name, type, linkToMedia, cost);
    }

}
