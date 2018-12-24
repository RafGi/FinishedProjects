package data;

import java.util.ArrayList;
import java.util.Objects;

public class Block {
    private int totalValue;
    private int xCord;
    private int yCord;
    private int oldx;
    private int oldy;
    private int height;
    private int width;
    private int dropperCounter;
    private int turnStage;
    private boolean reachedBottom;
    private String color;

    public Block(int value, String color) {
        height = 1;
        if (value > 8) {
            height++;
            if (value > 128) {
                height++;
                if (value > 2048) {
                    height++;
                }
            }
        }

        this.color = color;
        turnStage = 0;
        dropperCounter = 0;
        this.xCord = 4;
        yCord = 0;
        totalValue = value;
    }

    private void calcWidth() {
        width = 0;
        for (int i = 0; i < 4; i++) {
            for (int e = 0; e < 4; e++) {
                if (doesBlockAtCoordsExist(i, e)) {
                    if (i > width) {
                        width = i;
                    }
                }
            }
        }
        width++;
    }

    public int getWidth() {
        calcWidth();
        return width;
    }


    public void turn() {
        ArrayList<Boolean> fourByFour = new ArrayList<Boolean>();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                fourByFour.add(y * 4 + x, doesBlockAtCoordsExist(x, y));
            }
        }

        int newValue = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (fourByFour.get(y * 4 + x)) {
                    //turn volledig + lege rijen naar achterkant
                    newValue += Math.pow(16, x) * Math.pow(2, 3 - y);
                }
            }
        }

        totalValue = newValue;


        calcHeight();

        cleanUpWhiteSpace();
    }

    private void calcHeight() {
        height = 1;
        if (totalValue > 15) {
            height++;
            if (totalValue > 255) {
                height++;
                if (totalValue > 4095) {
                    height++;
                }
            }
        }
    }

    private void cleanUpWhiteSpace() {
        while (!doesBlockAtCoordsExist(0, 0)
                && !doesBlockAtCoordsExist(0, 1)
                && !doesBlockAtCoordsExist(0, 2)
                && !doesBlockAtCoordsExist(0, 3)) {
            totalValue = totalValue / 2;
        }
        while (!doesBlockAtCoordsExist(0, 0)
                && !doesBlockAtCoordsExist(1, 0)
                && !doesBlockAtCoordsExist(2, 0)
                && !doesBlockAtCoordsExist(3, 0)) {
            totalValue = totalValue / 16;
        }
    }

    public void drop() {
        if (!reachedBottom) {
            oldy = yCord;
            dropperCounter++;
            yCord++;
        }
    }

    public boolean doesBlockAtCoordsExist(int xPos, int yPos) {
        int baseValue = 1;
        double value = totalValue;
        boolean latestValue = false;

        for (int y = 3; y >= yPos; y--) {
            for (int x = 3; x >= 0; x--) {
                if ((y == yPos && x < xPos) || (y < yPos)) {
                    return latestValue;
                }

                if (x < 1) {
                    if (Math.pow(16, y) <= value) {
                        value -= Math.pow(16, y);
                        latestValue = true;
                    } else {
                        latestValue = false;
                    }
                } else if (y == 0) {
                    if (Math.pow(2, x) <= value) {
                        value -= Math.pow(2, x);
                        latestValue = true;
                    } else {
                        latestValue = false;
                    }
                } else if (baseValue * (Math.pow(16, y) * Math.pow(2, x)) <= value) {
                    value -= baseValue * ((Math.pow(16, y) * Math.pow(2, x)));
                    latestValue = true;
                } else {
                    latestValue = false;
                }

            }
        }
        return latestValue;
    }

    @Override
    public String toString() {
        StringBuilder r = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int e = 0; e < 4; e++) {

                String tussenWaarde = "F";
                if (doesBlockAtCoordsExist(e, i)) {
                    tussenWaarde = "T";
                }

                r.append(tussenWaarde);

                if (e < 3) {
                    r.append("-");
                }
            }
            r.append("\n");
        }
        return r.toString();
    }

    public int getTotalValue() {
        return totalValue;
    }

    public boolean doesBlockAtCoordsExistWithDrop(int x, int y) {
        return doesBlockAtCoordsExist(x, y - dropperCounter);
    }

    public boolean isReachedBottom() {
        return reachedBottom;
    }

    public void setReachedBottom(boolean reachedBottom) {
        this.reachedBottom = reachedBottom;
    }

    public int getyCord() {
        return yCord;
    }

    public void setyCord(int yCord) {
        this.yCord = yCord;
    }

    public int getxCord() {
        return xCord;
    }

    public void setxCord(int xCord) {
        this.xCord = xCord;
    }

    public int getHeight() {
        calcHeight();
        return height;
    }

    public int getOldx() {
        return oldx;
    }

    public void setOldx(int oldx) {
        this.oldx = oldx;
    }

    public int getOldy() {
        return oldy;
    }

    public void setOldy(int oldy) {
        this.oldy = oldy;
    }

    public String getColor() {
        return color;
    }

    public void clearLine(int yCord) {
        if (!(totalValue == 0)) {
            if ((this.yCord < yCord) && (this.yCord + getHeight() >= yCord)) {
                deleteCellsInLine((yCord - this.yCord) - 1);
                moveCellsBelowYcordUpOne((yCord - this.yCord) - 1);
                //block is on the line being cleared
            } else if (yCord >= this.yCord) {
                //block is above the line being cleared
                this.yCord++;

            }
        }
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTotalValue(),
                getxCord(),
                getyCord(),
                getOldx(),
                getOldy(),
                getHeight(),
                getWidth(),
                dropperCounter,
                turnStage,
                isReachedBottom(),
                getColor());
    }

    private void deleteCellsInLine(int i) {

        for (int xCord = 0; xCord < 4; xCord++) {
            if (doesBlockAtCoordsExist(xCord, i)) {
                deleteCell(xCord, i);
            }
        }
    }

    private void deleteCell(int xCord, int i) {
        totalValue -= Math.pow(16, i) * Math.pow(2, xCord);
    }


    private void moveCellsBelowYcordUpOne(int yCord) {
        for (int e = yCord; e > 0; e--) {
            for (int i = 0; i < 4; i++) {
                if (doesBlockAtCoordsExist(i, e - 1)) {
                    totalValue -= Math.pow(16, e - 1) * Math.pow(2, i);
                    totalValue += Math.pow(16, e) * Math.pow(2, i);
                }
            }
        }
    }
}

