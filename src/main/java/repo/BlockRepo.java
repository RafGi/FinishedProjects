package repo;

import data.Player;
import data.Block;

import java.util.List;

public interface BlockRepo {

    List<Block> getBlocks();

    Block getBlockById(int id);

    List<Block> getBlocksByColor(String color);

    Block getBlockByValue(int value);

}

