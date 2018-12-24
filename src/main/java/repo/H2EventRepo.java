package repo;

import data.Event;
import jdbcinteractor.JDBCInteractor;
import org.pmw.tinylog.Logger;
import repo.util.Strings;
import util.TetrisException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2EventRepo implements EventRepo {

    private int eventId = 0;

    @Override
    public List<Event> getEvents() {
        try (Statement stmt = JDBCInteractor.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(Strings.SELECT_EVENTS);
            List<Event> allEvents = new ArrayList<>();
            while (rs.next()) {
                Event event = this.resultset2Event(rs);
                allEvents.add(event);
            }
            stmt.close();
            return allEvents;

        } catch (SQLException ex) {
            Logger.warn("Error retrieving events", ex.getLocalizedMessage());
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve all events", ex);
        }
    }

    @Override
    public List<Event> getEventsByCost() {
        return null;
    }

    public List<Event> getEventsByCost(double cost) {
        try (Connection con = JDBCInteractor.getConnection();
             PreparedStatement prep = con.prepareStatement(Strings.SELECT_EVENTS_BY_COST)) {
            prep.setDouble(1, cost);

            try (ResultSet rs = prep.executeQuery()) {
                List<Event> allEvents = new ArrayList<>();
                while (rs.next()) {
                    Event event = this.resultset2Event(rs);
                    allEvents.add(event);
                }
                return allEvents;
            }
        } catch (SQLException e) {
            throw new TetrisException("Couldn't retrieve events by Cost", e);
        }
    }

    @Override
    public void addEvent(Event event) {
        try (Connection con = JDBCInteractor.getConnection();
             PreparedStatement prep = con.prepareStatement(Strings.ADD_EVENT)) {
            prep.setInt(1, eventId);
            prep.setString(2, event.getEffect());
            prep.setString(3, event.getName());
            prep.setDouble(4, event.getCost());

            prep.executeUpdate();
            eventId++;
        } catch (SQLException e) {
            throw new TetrisException("Unable to add event", e);
        }
    }

    @Override
    public void deleteEvent(Event event) {
        try (Connection con = JDBCInteractor.getConnection();
             PreparedStatement prep = con.prepareStatement(Strings.DELETE_EVENT)) {
            prep.setInt(1, event.getId());
            prep.executeUpdate();
        } catch (SQLException e) {
            throw new TetrisException("Unable to delete player from database", e);
        }
    }

    @Override
    public Event getEventById(int id) {
        try (Connection con = JDBCInteractor.getConnection();
             PreparedStatement prep = con.prepareStatement(Strings.SELECT_EVENT_BY_ID)) {
            prep.setInt(1, id);

            try (ResultSet rs = prep.executeQuery()) {
                Event event = null;
                while (rs.next()) {
                    event = this.resultset2Event(rs);

                }
                return event;
            }
        } catch (SQLException e) {
            throw new TetrisException("Couldn't retrieve event by ID", e);
        }
    }

    private Event resultset2Event(ResultSet rs) throws SQLException {
        int id = rs.getInt("eventid");
        String eventname = rs.getString("eventname");
        String effect = rs.getString("effect");
        double cost = rs.getDouble("cost");


        return new Event(id, eventname, effect, cost);
    }
}
