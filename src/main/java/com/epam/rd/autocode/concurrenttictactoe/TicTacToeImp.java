package com.epam.rd.autocode.concurrenttictactoe;

import java.util.Arrays;

public class TicTacToeImp implements TicTacToe {
    private char[][] table;
    private char lastMark;

    public TicTacToeImp() {
        this.table = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = ' ';
            }
        }
        this.lastMark = ' ';

        // Print the array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("[" + table[i][j] + "]");
            }
            System.out.println();
        }
    }


    /**
     * Sets a mark in cell with specified coordinates.
     *
     * @param x    - x coordinate.
     * @param y    - y coordinate.
     * @param mark - mark to set.
     */
    @Override
    public synchronized void setMark(int x, int y, char mark) {

        if (mark == 'X' && table[x][y] == ' ') {
            System.out.println("setting " + mark + " at position (" + x + ", " + y + ").");
            table[x][y] = mark;
            System.out.println(Arrays.deepToString(table));
            lastMark = mark; // update last mark
        } else if (mark == 'O' && table[x][y] == ' ') {
            System.out.println("setting " + mark + " at position (" + x + ", " + y + ").");
            table[x][y] = mark;
            System.out.println(Arrays.deepToString(table));
            lastMark = mark; // update last mark
        } else {
            throw new IllegalArgumentException();
        }

    }

    /**
     * Returns a COPY of current table with marks.
     * Note, edit of that copy should not affect the source TicTacToe object.
     *
     * @return a copy of current table.
     */
    @Override
    public char[][] table() {
        char[][] copyTable = new char[3][3];

//        Copy elements from original table to the new array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copyTable[i][j] = table[i][j];
            }
        }

        return copyTable;
    }


    /**
     * Returns last mark that was set in a table.
     *
     * @return last mark that was set in a table.
     */
    @Override
    public char lastMark() {
        return lastMark;
    }


}