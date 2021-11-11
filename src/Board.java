package battleship;

public abstract class Board implements CoordinatesProcessor {

    final char[][] boardSquares;
    int player;

    Board(int player) {
        this.player = player;
        boardSquares = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boardSquares[i][j] = '~';
            }
        }
    }

    void printBoard() {
        System.out.print("\n" + "  1 2 3 4 5 6 7 8 9 10" + "\n");
        for (int i = 0; i < boardSquares.length; i++) {
            System.out.printf("%s ", (char) ('A' + i));
            for (int j = 0; j < boardSquares.length - 1; j++) {
                System.out.printf("%s ", boardSquares[i][j]);
            }
            System.out.printf("%s%n", boardSquares[i][boardSquares.length - 1]);
        }
        System.out.println();
    }

}
