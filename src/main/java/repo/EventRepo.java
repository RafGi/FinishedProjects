package repo;

import data.Event;
import data.Player;

import java.util.List;

public interface EventRepo {

    List<Event> getEvents();
    List<Event> getEventsByCost();
    void addEvent(Event event);
    void deleteEvent(Event event);
    Event getEventById(int id);
}
