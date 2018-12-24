package repo;

import data.Block;
import jdbcinteractor.JDBCInteractor;
import repo.util.Strings;
import org.pmw.tinylog.Logger;
import util.TetrisException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2BlockRepo implements BlockRepo {

    private static final String FIELD_BLOCKID = "blockid";
    private static final String FIELD_BLOCKCOLOR = "blockcolor";
    private static final String FIELD_BLOCKVALUE = "blockvalue";

    @Override
    public List<Block> getBlocks() {
        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(Strings.SELECT_BLOCKS)) {

            ResultSet rs = prep.executeQuery();
            List<Block> allBlocks = new ArrayList<>();

            while (rs.next()) {
                Block block = this.resultset2Block(rs);
                allBlocks.add(block);
            }

            prep.close();
            return allBlocks;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve all blocks", ex);
        }
    }

    @Override
    public List<Block> getBlocksByColor(String color) {
        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(Strings.SELECT_BLOCKS_BY_COLOR)) {

            prep.setString(1, color);
            ResultSet rs = prep.executeQuery();
            List<Block> allBlocksByColor = new ArrayList<>();

            while (rs.next()) {
                Block block = this.resultset2Block(rs);
                allBlocksByColor.add(block);
            }

            prep.close();
            return allBlocksByColor;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve allBlocksByColor", ex);
        }
    }

    @Override
    public Block getBlockByValue(int value) {
        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(Strings.SELECT_BLOCKS_BY_VALUE)) {

            prep.setInt(1, value);
            ResultSet rs = prep.executeQuery();
            Block block = this.resultset2Block(rs);
            prep.close();
            return block;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve blockbyvalue", ex);
        }

    }

    @Override
    public Block getBlockById(int id) {
        try (PreparedStatement prep = JDBCInteractor.getConnection().prepareStatement(Strings.SELECT_BLOCKS_BY_ID)) {

            prep.setInt(1, id);
            ResultSet rs = prep.executeQuery();
            Block block = this.resultset2Block(rs);
            prep.close();
            return block;

        } catch (SQLException ex) {
            Logger.debug(ex.getStackTrace());
            throw new TetrisException("Couldn't retrieve blockbyid", ex);
        }

    }

    private Block resultset2Block(ResultSet rs) throws SQLException {
        int id = rs.getInt(FIELD_BLOCKID);
        String color = rs.getString(FIELD_BLOCKCOLOR);
        int value = rs.getInt(FIELD_BLOCKVALUE);

        return new Block(value, color);
        //return new Block(id, color, value);
    }

}
