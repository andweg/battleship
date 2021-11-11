package battleship;

public class PlayerBoard extends Board {

    public PlayerBoard(int player) {
        super(player);
        placeShips();
    }

    private void placeShips() {
        System.out.printf("Player %d, place your ships on the game field %n", player);
        printBoard();
        for (ShipType t : ShipType.values()) {
            addNewShip(new Ship(t, boardSquares));
            printBoard();
        }
        UserInput.enterPrompt();
    }

    private void addNewShip(Ship newShip) {
        if (!newShip.isVertical()) {
            for (int i = 0; i < 10; i++) {
                if (i >= Math.min(newShip.getBow()[0], newShip.getStern()[0])
                        &&  i <= Math.max(newShip.getBow()[0], newShip.getStern()[0])) {
                    boardSquares[i][newShip.getBow()[1]] = 'O';
                }
            }
        } else {
            for (int i = 0; i < 10; i++) {
                if (i >= Math.min(newShip.getBow()[1], newShip.getStern()[1])
                        && i <= Math.max(newShip.getBow()[1], newShip.getStern()[1])) {
                    boardSquares[newShip.getBow()[0]][i] = 'O';
                }
            }
        }
    }

    char checkHit(int[] coordinates) {
        char result;
        switch (boardSquares[coordinates[0]][coordinates[1]]) {
            case 'O':
                result = boardSquares[coordinates[0]][coordinates[1]] = 'X';
                checkHitEffect(coordinates);
                return result;
            case 'X':
                result = boardSquares[coordinates[0]][coordinates[1]] = 'X';
                System.out.println("You had already shot at this coordinates before (and hit a ship).");
                return result;
            case '~':
                result = boardSquares[coordinates[0]][coordinates[1]] = 'M';
                System.out.println("You missed!");
                return result;
            case 'M':
                result = boardSquares[coordinates[0]][coordinates[1]] = 'M';
                System.out.println("You had already shot at this coordinates before (and missed).");
                return result;
            default:
                return result = boardSquares[coordinates[0]][coordinates[1]] = 'M';
        }
    }


    private void checkHitEffect(int[] coordinates) {
        if (wasAShipSank(coordinates)) {
            if (isTheGameOver()) {
                System.out.println("You sank the last ship. You won. Congratulations!");
                System.exit(0);
            }
            System.out.println("You sank a ship!");
        } else {
            System.out.println("You hit a ship!");
        }
    }

    private boolean wasAShipSank(int[] coordinates) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                try {
                    if (boardSquares[coordinates[0] + i][coordinates[1] + j] == 'O') {
                        return false;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
        return true;
    }

    private boolean isTheGameOver() {
        for (int i = 0; i < 10; i++) {
            for (char c : boardSquares[i]) {
                if (c == 'O') {
                    return false;
                }
            }
        }
        return true;
    }

}
