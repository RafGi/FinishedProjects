package repo;

import data.Achievement;
import jdbcinteractor.JDBCInteractor;
import repo.util.Strings;
import util.TetrisException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class H2AchievementRepo implements AchievementRepo {

    private static final String FIELD_ACHIEVEMENTID = "achievementid";
    private static final String FIELD_ACHIEVEMENTNAME = "achievementname";
    private static final String FIELD_ACHIEVEMENTDESCRIPTION = "achievementdescription";
    private static final String FIELD_ACHIEVEMENTREQUIREMENT = "achievementrequirement";
    private static final String FIELD_ACHIEVEMENTCOINREWARD = "achievementcoinreward";
    private static final String FIELD_ACHIEVEMENTEXPREWARD = "achievementexpreward";

    @Override
    public Achievement getAchievementsByPlayerId(int id) {
        try (Connection con = JDBCInteractor.getConnection();
             PreparedStatement prep = con.prepareStatement(Strings.SELECT_ACHIEVEMENTS_BY_PLAYERID)) {

            prep.setInt(1, id);

            try (ResultSet rs = prep.executeQuery()) {
                System.out.println(rs);
                Achievement achievement = null;
                while (rs.next()) {
                    System.out.println(rs);
                    System.out.println(achievement);
                    achievement = this.resultset2Achievement(rs);
                    System.out.println(achievement);

                }
                return achievement;
            }
        } catch (SQLException e) {
            throw new TetrisException("Couldn't retrieve achievements by playerid", e);
        }
    }

    private Achievement resultset2Achievement(ResultSet rs) throws SQLException {
        int achievementid = rs.getInt(FIELD_ACHIEVEMENTID);
        String achievementname = rs.getString(FIELD_ACHIEVEMENTNAME);
        String achievementdescription = rs.getString(FIELD_ACHIEVEMENTDESCRIPTION);
        String achievementrequirement = rs.getString(FIELD_ACHIEVEMENTREQUIREMENT);
        int achievementcoinreward = rs.getInt(FIELD_ACHIEVEMENTCOINREWARD);
        double achievementexpreward = rs.getDouble(FIELD_ACHIEVEMENTEXPREWARD);

        return new Achievement(achievementid,
                achievementname,
                achievementdescription,
                achievementrequirement,
                achievementcoinreward,
                achievementexpreward);
    }

}
