package data;

import repo.Repositories;

import java.util.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private int score;
    private int lineCleared;
    private int combo;
    private int cooldown;
    private int timezone;
    private boolean gameOver;
    private Timer gameTickTimer;
    private Timer cooldownTimer;
    private HashMap<Integer, String> gameMap;
    private Random random;
    //todo:remove cli testing part and replace with frontend inputs
    private String currentMomentum = "$";
    private SinglePlayerField gameField;
    private Block currentDroppingBlock;
    private ArrayList<Block> allPreviousBlocks;
    private int gameTicks;
    private int gameWidth;
    private int gameHeight;
    private List<Block> allBlocks;

    public Game() {
        initialize();
    }

    private void initialize() {
        cooldown = 0;
        timezone = 1;
        score = 0;
        lineCleared = 0;
        fillArrayFromDatabase();
        gameTicks = 0;
        gameWidth = 10;
        gameHeight = 20;

        gameTickTimer = new Timer();

        allPreviousBlocks = new ArrayList<>();

        Player currentPlayer = new Player(1,
                "username",
                300,
                3000,
                "https://www.clipartmax.com/png/middle/223-2235622_space-"
                        + "invaders-clipart-ship-space-invader-sprite-png.png",
                3,
                "dep@thuis.be",
                "tisgeenhash");
        //todo replace mockdata with database data ^

        gameMap = new HashMap<>();

        gameField = new SinglePlayerField(currentPlayer, "Normal", 1, 0, gameTicks, gameMap);
        gameField.createPlayfield();
        createRandomBlock();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                gameTick();
            }
        };

        gameTickTimer.scheduleAtFixedRate(task, 0, 300);
    }

    private void fillArrayFromDatabase() {
        allBlocks = new ArrayList<>();
        allBlocks = Repositories.getBlockRepository().getBlocks();
    }

    private void createRandomBlock() {
        random = new Random();
        int i = random.nextInt(7);
        createBlock(i);
    }

    private void createBlock(int i) {
        currentDroppingBlock = new Block(allBlocks.get(i).getTotalValue(), allBlocks.get(i).getColor());
    }

    private void gameOver() {
        gameOver = true;
        gameTickTimer.cancel();
    }


    public void turn() {
        currentDroppingBlock.turn();
    }

    public boolean readyForPower() {
        return cooldown < 1;
    }

    private void addScore(int i) {
        score += i;

        if (score > 250) {
            timezone = 2;
        }
        if (score > 600) {
            timezone = 3;
        }
    }

    public void moveDown() {
        if (!gameOver) {
            addScore(1);
            checkIfCurrentBlockHinderedOrMoveDown();
        }
    }


    private void gameTick() {
        for (int i = 200; i < gameMap.size(); i++) {
            gameMap.remove(i);
        }
        refreshPlayingfield();
    }

    private void checkIfCurrentBlockHinderedOrMoveDown() {
        boolean isTheTetrisBlockHinderedFromGoingDown = false;

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                boolean isABlock = currentDroppingBlock.doesBlockAtCoordsExist(x, y);
                boolean hasBlockBelowThatIsPartOfTheSameStructure
                        = currentDroppingBlock.doesBlockAtCoordsExist(x, y + 1);

                boolean hasABlockBelowThatIsNotPartOfStructure = false;
                boolean reachedBottom = false;

                if ((((currentDroppingBlock.getyCord() + y + 1)
                        * gameWidth
                        + currentDroppingBlock.getxCord() + x) > 200)
                        && isABlock) {
                    reachedBottom = true;
                }
                if (!reachedBottom && isABlock) {
                    if ((currentDroppingBlock.getyCord() + y + 1)
                            * gameWidth + currentDroppingBlock.getxCord() + x < 200) {
                        hasABlockBelowThatIsNotPartOfStructure
                                = !gameMap.get((currentDroppingBlock.getyCord() + y + 1)
                                * gameWidth + currentDroppingBlock.getxCord() + x).equals("0");

                    } else {
                        hasABlockBelowThatIsNotPartOfStructure = true;
                    }
                }
                if ((isABlock
                        && !hasBlockBelowThatIsPartOfTheSameStructure
                        && hasABlockBelowThatIsNotPartOfStructure)
                        || reachedBottom) {
                    isTheTetrisBlockHinderedFromGoingDown = true;
                }
            }
        }

        if (!isTheTetrisBlockHinderedFromGoingDown) {
            currentDroppingBlock.drop();
        }

        if (isTheTetrisBlockHinderedFromGoingDown) {

            if (currentDroppingBlock.getyCord() <= 1) {
                gameOver();
            }

            currentDroppingBlock.setReachedBottom(true);
            //currentDroppingBlock.setyCord(currentDroppingBlock.getyCord()+1);
            allPreviousBlocks.add(currentDroppingBlock);

            checkForCompletedLines();
            createRandomBlock();
        }
    }

    private void checkForCompletedLines() {
        int tussenGetalPerLijn = 0;
        combo = 0;
        for (int i = 0; i < 200; i++) {
            if (i % 10 == 0) {
                tussenGetalPerLijn = 0;
            }

            if (!gameMap.get(i).equals("0")) {
                tussenGetalPerLijn++;
            } else {
                tussenGetalPerLijn = 0;
            }

            if (tussenGetalPerLijn == 10) {
                combo++;
                switch (combo) {
                    case 1:
                        addScore(80);
                        break;
                    case 2:
                        addScore(100);
                        break;
                    case 3:
                        addScore(125);
                        break;
                    case 4:
                        addScore(160);
                        break;
                    default:
                        break;
                }
                clearLine((i + 1) / 10);
                refreshPlayingfield();
                i = 0;
            }
        }
    }

    private void clearLine(int i) {
        lineCleared++;
        for (Block b : allPreviousBlocks) {
            b.clearLine(i);
        }
    }

    public void dropDown() {
        currentDroppingBlock.setOldx(currentDroppingBlock.getxCord());
        currentDroppingBlock.setOldy(currentDroppingBlock.getyCord());
        currentDroppingBlock.setyCord(gameHeight - currentDroppingBlock.getHeight());
        createRandomBlock();
    }

    public void moveRight() {
        if (currentDroppingBlock.getxCord() < (gameWidth - currentDroppingBlock.getWidth())) {
            currentDroppingBlock.setOldx(currentDroppingBlock.getxCord());
            currentDroppingBlock.setOldy(currentDroppingBlock.getyCord());
            currentDroppingBlock.setxCord(currentDroppingBlock.getxCord() + 1);
        }
    }

    public void moveLeft() {
        if (currentDroppingBlock.getxCord() > 0) {
            currentDroppingBlock.setOldx(currentDroppingBlock.getxCord());
            currentDroppingBlock.setOldy(currentDroppingBlock.getyCord());
            currentDroppingBlock.setxCord(currentDroppingBlock.getxCord() - 1);
        }

    }


    public boolean getGameOver() {
        return gameOver;
    }

    public HashMap<Integer, String> getMap() {
        return gameMap;
    }

    private void refreshPlayingfield() {
        gameField.elapsedTimeSeconds = gameTicks;
        gameField.playfield = gameMap;

        if (!(currentDroppingBlock == null)) {
            gameField.createPlayfield();
            putInGameMap(currentDroppingBlock);
        }

        for (Block previousBlock : allPreviousBlocks) {
            putInGameMap(previousBlock);
        }
        checkIfCurrentBlockHinderedOrMoveDown();
    }

    private void putInGameMap(Block block) {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                int xCord = block.getxCord();
                int yCord = block.getyCord();
                boolean a = block.doesBlockAtCoordsExist(x, y);
                String color = "0";
                if (a) {
                    color = block.getColor();
                    gameMap.put(xCord + x + ((yCord + y) * gameWidth), color);
                }

            }
        }
    }

    public int getGameTicks() {
        return gameTicks;
    }

    public int getScore() {
        return score;
    }

    public int getTimezone() {
        return timezone;
    }

    public void usePower() {
        //todo: get heropower from database and use switch case here to implement hero powers

        if (cooldown == 0) {
            //timezone 1 power is offensive so the logic isn't here but in gameconsumer
            if (timezone == 1) {
                //turn other players brick
                cooldown = 10;
            }
            if (timezone == 2) {
                //blacksmith
                currentDroppingBlock = new Block(19, "#6da903");
                cooldown = 20;
            }
            if (timezone == 3) {
                //regeneration
                clearLine(20);
                cooldown = 30;
            }
            cooldownTimer = new Timer();
            TimerTask lowerCooldown = new TimerTask() {
                @Override
                public void run() {
                    lowerCooldown();
                }
            };
            cooldownTimer.scheduleAtFixedRate(lowerCooldown, 1000, 1000);
        }
    }

    private void lowerCooldown() {
        cooldown--;

        if (cooldown == 0) {
            cooldownTimer.cancel();
        }
    }

    public int getCooldown() {
        return cooldown;
    }
}
