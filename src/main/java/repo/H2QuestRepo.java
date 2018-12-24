package repo;

import data.Quest;
import jdbcinteractor.JDBCInteractor;
import org.pmw.tinylog.Logger;
import repo.util.Strings;
import util.TetrisException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class H2QuestRepo implements QuestRepo {

    private int questId = 0;

    private static final String FIELD_QUESTID = "questid";
    private static final String FIELD_QUESTNAME = "questname";
    private static final String FIELD_QUESTDISCRIPTION = "questdescription";
    private static final String FIELD_REQUIREMENT = "requirement";
    private static final String FIELD_QUESTREWARDEXP = "questrewardexp";

    @Override
    public List<Quest> getAllQuests() {

        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(Strings.SELECT_QUESTS)) {

            ResultSet rs = prep.executeQuery();
            List<Quest> allQuests = new ArrayList<>();

            while (rs.next()) {
                Quest quest = this.resultset2Quest(rs);
                allQuests.add(quest);
            }

            prep.close();
            return allQuests;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve all quests", ex);
        }
    }

    @Override
    public Quest getQuestById(int id) {
        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(Strings.SELECT_QUESTS_BY_ID)) {

            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            Quest quest = this.resultset2Quest(rs);
            prep.close();
            return quest;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve questbyid", ex);
        }

    }

    @Override
    public Quest getQuestsByName(String name) {
        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(Strings.SELECT_QUESTS_BY_NAME)) {

            prep.setString(1, name);
            ResultSet rs = prep.executeQuery();
            Quest quest = this.resultset2Quest(rs);
            prep.close();
            return quest;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve questbyname", ex);
        }

    }

    @Override
    public Quest getQuestByDescription(String description) {
        try (PreparedStatement prep = JDBCInteractor.getConnection()
                .prepareStatement(Strings.SELECT_QUESTS_BY_DESCRIPTION)) {

            prep.setString(1, description);
            ResultSet rs = prep.executeQuery();
            Quest quest = this.resultset2Quest(rs);
            prep.close();
            return quest;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve questbydescription", ex);
        }

    }

    @Override
    public Quest getQuestByRequirement(String requirement) {
        try (PreparedStatement prep = JDBCInteractor.getConnection()
                .prepareStatement(Strings.SELECT_QUESTS_BY_REQUIREMENT)) {

            prep.setString(1, requirement);
            ResultSet rs = prep.executeQuery();
            Quest quest = this.resultset2Quest(rs);
            prep.close();
            return quest;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve questbyrequirement", ex);
        }

    }

    @Override
    public void addQuest(Quest quest) {
        try (Connection con = JDBCInteractor.getConnection();
             PreparedStatement prep = con.prepareStatement(Strings.ADD_QUEST)) {
            prep.setInt(1, questId);
            prep.setString(2, quest.getName());
            prep.setString(3, quest.getDescription());
            prep.setString(4, quest.getRequirement());
            prep.setDouble(5, quest.getQuestrewardexperience());

            prep.executeUpdate();
            questId++;
        } catch (SQLException e) {
            throw new TetrisException("Unable to add block", e);
        }
    }


    @Override
    public void deleteQuest(Quest quest) {
        try (Connection con = JDBCInteractor.getConnection();
             PreparedStatement prep = con.prepareStatement(Strings.DELETE_QUEST)) {
            prep.setInt(1, quest.getQuestID());
            prep.executeUpdate();
        } catch (SQLException e) {
            throw new TetrisException("Unable to delete quest from database", e);
        }
    }

    private Quest resultset2Quest(ResultSet rs) throws SQLException {
        int questid = rs.getInt(FIELD_QUESTID);
        String questname = rs.getString(FIELD_QUESTNAME);
        String questdescription = rs.getString(FIELD_QUESTDISCRIPTION);
        String requirement = rs.getString(FIELD_REQUIREMENT);
        double questrewardexp = rs.getDouble(FIELD_QUESTREWARDEXP);

        return new Quest(questid, questname, questdescription, requirement, questrewardexp);
    }
}
