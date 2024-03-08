package com.epam.rd.autocode.concurrenttictactoe;

public class PlayerImp implements Player {
    private TicTacToe ticTacToe;
    private final char mark;
    private PlayerStrategy playerStrategy;

    public PlayerImp(TicTacToe ticTacToe, final char mark, PlayerStrategy playerStrategy) {
        this.ticTacToe = ticTacToe;
        this.mark = mark;
        this.playerStrategy = playerStrategy;
    }

    @Override
    public void run() {
        System.out.println("Player has been created with mark: " + mark);

        while (!checkWin() && !checkDraw()) {

            if ((mark == 'X' && !hasAnyElement()) || (mark == 'X' && ticTacToe.lastMark() == 'O')) {
                if (!checkWin() && !checkDraw()) {
                    char[][] table = ticTacToe.table();
                    Move move = playerStrategy.computeMove(mark, ticTacToe);
                    int row = move.row;
                    int column = move.column;

                    if (table[row][column] == ' ') {
                        System.out.println("For " + mark + " Passing row: " + row + ", column: " + column);
                        ticTacToe.setMark(row, column, mark);
                    }
                }
            } else if (mark == 'O' && ticTacToe.lastMark() == 'X') {
                if (!checkWin() && !checkDraw()) {
                    char[][] table = ticTacToe.table();
                    Move move = playerStrategy.computeMove(mark, ticTacToe);
                    int row = move.row;
                    int column = move.column;

                    if (table[row][column] == ' ') {
                        System.out.println("For " + mark + " Passing row: " + row + ", column: " + column);
                        ticTacToe.setMark(row, column, mark);
                    }
                }
            }

        }
        /*
        we should have 2 implementations for x and o
        they should watch table and they must consider rules
        o should not be first so it must check if last mark is x, then it should check
        if coordinates is valid and if they are not it must generate new coordinates until it's valid.
        meanwhile x must make its first turn and wait until o finds valid coordinates and makes move, then
        last mark becomes o so x can continue and check if its coordinates are valid and generate until they are.
        Then x ill make its second move, and again
        o will check valid coordinates and so on...
         */

        if (checkWin()) {
            char[][] table = ticTacToe.table();
            System.out.println(ticTacToe.lastMark() + " IS THE WINNER!");
//           print board:
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print("[" + table[i][j] + "]");
                }
                System.out.println();
            }
        } else if (checkDraw()) {
            char[][] table = ticTacToe.table();
            System.out.println("THE GAME IS TIE!");
//            print board:
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print("[" + table[i][j] + "]");
                }
                System.out.println();
            }
        }

    }

    public boolean hasAnyElement() {
        char[][] table = ticTacToe.table();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == 'X' || table[i][j] == 'O') {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean checkWin() {
        char[][] table = ticTacToe.table();
        char mark = ticTacToe.lastMark(); // Get the last mark that was set
        if (mark == ' ') {
            return false; // No moves made yet, so no winner
        }

        // Check rows, columns, and diagonals for a winning configuration
        for (int i = 0; i < 3; i++) {
            if (table[i][0] == mark && table[i][1] == mark && table[i][2] == mark) {
                return true; // Row i has all marks equal to 'mark'
            }
            if (table[0][i] == mark && table[1][i] == mark && table[2][i] == mark) {
                return true; // Column i has all marks equal to 'mark'
            }
        }
        // Check diagonals
        if (table[0][0] == mark && table[1][1] == mark && table[2][2] == mark) {
            return true; // Diagonal from top-left to bottom-right has all marks equal to 'mark'
        }
        if (table[0][2] == mark && table[1][1] == mark && table[2][0] == mark) {
            return true; // Diagonal from top-right to bottom-left has all marks equal to 'mark'
        }

        return false; // No winning configuration found

    }

    public boolean checkDraw() {
        char[][] table = ticTacToe.table();

        if (!checkWin()) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (table[i][j] == ' ') {
                        return false; // At least one cell is empty, game is not a draw
                    }
                }
            }
            return true;  // All cells are filled, game is a draw
        }

        return false; // Game is not yet finished or a player has won
    }
}
