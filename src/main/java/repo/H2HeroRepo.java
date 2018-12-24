package repo;

import data.Hero;
import io.vertx.ext.jdbc.JDBCClient;
import jdbcinteractor.JDBCInteractor;
import repo.util.Strings;
import org.pmw.tinylog.Logger;
import util.TetrisException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2HeroRepo implements HeroRepo {

    private static final String FIELD_ID = "heroid";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_COST = "cost";
    private static final String FIELD_REQLVL = "requiredlevel";
    private static final String FIELD_AVATAR = "avatar";
    private static final String FIELD_ERAID = "eraid";
    private static final String FIELD_HEROPWR = "heropower";

    private JDBCClient jdbcClient;

    H2HeroRepo() {
    }

    public H2HeroRepo(final JDBCClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Hero> getHeroes() {

        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(Strings.SELECT_HEROES)) {

            ResultSet rs = prep.executeQuery();
            List<Hero> allHeroes = new ArrayList<>();

            while (rs.next()) {
                Hero hero = this.resultset2Hero(rs);
                allHeroes.add(hero);
            }

            prep.close();
            return allHeroes;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve all heroes", ex);
        }
    }

    @Override
    public List<Hero> getHeroesByPlayer(int playerid) {

        try (PreparedStatement prep = JDBCInteractor.getConnection()
                .prepareStatement(Strings.SELECT_HEROES_BY_PLAYER)) {

            prep.setInt(1, playerid);
            ResultSet rs = prep.executeQuery();
            List<Hero> playerHeroes = new ArrayList<>();

            while (rs.next()) {
                Hero hero = this.resultset2Hero(rs);
                playerHeroes.add(hero);
            }

            prep.close();
            return playerHeroes;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve all playerheroes", ex);
        }
    }

    @Override
    public Hero getHeroById(int id) {
        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(Strings.SELECT_HERO_BY_ID)) {

            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            Hero hero = this.resultset2Hero(rs);
            prep.close();
            return hero;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve all playerheroes", ex);
        }

    }

    @Override
    public List<Hero> getHeroByCost(int cost) {
        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(Strings.SELECT_HERO_BY_COST)) {

            prep.setInt(1, cost);
            ResultSet rs = prep.executeQuery();
            List<Hero> allheroesbycost = new ArrayList<>();

            while (rs.next()) {
                Hero hero = this.resultset2Hero(rs);
                allheroesbycost.add(hero);
            }

            prep.close();
            return allheroesbycost;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve all heroesbycost", ex);
        }
    }

    @Override
    public List<Hero> getHeroByReqLevel(int reqLvl) {
        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(Strings.SELECT_HERO_BY_REQLVL)) {

            prep.setInt(1, reqLvl);
            ResultSet rs = prep.executeQuery();
            List<Hero> allheroesbyreqlvl = new ArrayList<>();

            while (rs.next()) {
                Hero hero = this.resultset2Hero(rs);
                allheroesbyreqlvl.add(hero);
            }

            prep.close();
            return allheroesbyreqlvl;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve all heroesbyreqlvl", ex);
        }
    }

    @Override
    public void addHero(Hero hero) {
        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(Strings.ADD_HERO)) {
            prep.setString(1, hero.getName());
            prep.setDouble(2, hero.getCost());
            prep.setString(3, hero.getLinkToAvatar());
            prep.setInt(4, hero.getEraID());
            prep.setInt(5, hero.getReqLevel());
            prep.executeUpdate();
        } catch (SQLException ex) {
            throw new TetrisException("Unable to add hero into database", ex);
        }
    }

    @Override
    public void deleteHero(Hero hero) {
        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(Strings.DELETE_HERO)) {
            prep.setInt(1, hero.getId());
            prep.executeUpdate();
        } catch (SQLException ex) {
            throw new TetrisException("Unable to delete hero from database", ex);
        }
    }

    private Hero resultset2Hero(ResultSet rs) throws SQLException {
        int id = rs.getInt(FIELD_ID);
        String name = rs.getString(FIELD_NAME);
        double cost = rs.getDouble(FIELD_COST);
        String avatar = rs.getString(FIELD_AVATAR);
        int eraid = rs.getInt(FIELD_ERAID);
        int reqlvl = rs.getInt(FIELD_REQLVL);
        String heroPower = rs.getString(FIELD_HEROPWR);

        return new Hero(id, name, reqlvl, cost, avatar, eraid);
    }
}
