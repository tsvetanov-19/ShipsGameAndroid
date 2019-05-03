package com.example.ttsvetanov.shipsgame.game;

import java.util.Random;

/**
 * Square board where ships are placed
 * Square values stored in single-dimensional array
 * square value = 0 if no ship
 * square value = 1 if ship1 is on the square
 * ... etc
 * square value = 5 if ship5 is on the square
 * Created by ttsvetanov on 02.03.17.
 */

public class CheckerBoard {
    protected final static int BOARD_SIDE = 8;
    protected final static int BOARD_SIZE = BOARD_SIDE * BOARD_SIDE;
    protected int[] board;

    /**
     * initialize board with empty square fields
     */
    public CheckerBoard() {
        board = new int[CheckerBoard.BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            this.board[i] = 0;
        }
    }

    public void setSquare(int id, int value) {
        int x = id % CheckerBoard.BOARD_SIDE;
        int y = id / CheckerBoard.BOARD_SIDE;
        this.board[id] = value;
    }

    public void setSquareArray(int[] ids, int value) {
        for (int id : ids) {
            this.board[id] = value;
        }
    }

    public int getSquare(int id) {
        return board[id];
    }


    public void setShips(int[] ships, boolean[] isVertical) {
        int shipIndex = 0;
        if (ships.length > 0) {
            for (int size : ships) {
                if (size > 0) {
                    setCurrentShip(size, isVertical[shipIndex], shipIndex + 1);
                    shipIndex++;
                }
            }
        }
    }

    /**
     * @param size       integer -  size of the ship in squares - range[0,5]
     * @param isVertical boolean - true if ship vertically placed, false if horizontally placed
     * @param shipIndex  integer - index of ship - range[1,5] array indices shifted by 1
     */
    protected void setCurrentShip(int size, boolean isVertical, int shipIndex) {

        int startCoord = 0;
        int endCoord = CheckerBoard.BOARD_SIDE;
        int[] squareId = new int[size];
        int id = 0;

        //find ship place by randomly choosing start position
        // repeat until suitable place for the ship is found
        do {
            //Set random start position
            if (id == 0) {
                int row = CheckerBoard.randInt(startCoord, endCoord);
                int col = CheckerBoard.randInt(startCoord, endCoord);
                squareId[id] = col * endCoord + row;
                //make sure ship doesn't overflow row/column
                boolean in_space_limit = (
                    //horizontal piece -> check row limit
                    (
                        (!isVertical) && (row + size < CheckerBoard.BOARD_SIDE)
                    )
                        ||
                    //vertical piece -> check column limit
                    (
                        (isVertical) && (col + size < CheckerBoard.BOARD_SIDE)
                    )
                );
                if (!in_space_limit) {
                    //reset
                    squareId[id] = -1;
                    id = 0;
                    continue;
                }

            } else {
                if (isVertical) {
                    // add one row to previous square
                    squareId[id] = squareId[id - 1] + CheckerBoard.BOARD_SIDE;
                } else {
                    //add one column to previous square
                    squareId[id] = squareId[id - 1] + 1;
                }
            }

            if (squareOccupied(squareId[id])) {
                //reset
                squareId[id] = -1;
                id = 0;
            } else {
                id++;
            }
        }
        //loop until all ship squares are properly placed
        while (id < size);
        // then save the squares we found to the checkerboard
        setSquareArray(squareId, shipIndex);

    }

    /**
     * self explainatory method name
     *
     * @param squareId integer - range[0, MAX_BOARD_INDEX]
     * @return true if occupied, false if square is free
     */

    protected boolean squareOccupied(int squareId) {
        // square out of bounds or occupied
        if ( (squareId < 0 || squareId > CheckerBoard.BOARD_SIZE) || (getSquare(squareId) > 0)) {
            return true;
        }

        return false;
    }

    /**
     * Method returns random integer in a given range inclusively
     *
     * @param min floor
     * @param max ceil
     * @return random integer in range [min, max]
     */
    protected static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

}
