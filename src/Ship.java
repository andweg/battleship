package battleship;

public class Ship implements CoordinatesProcessor {

    private int[] bow;
    private int[] stern;
    private boolean vertical;

    public int[] getBow() {
        return bow;
    }
    public int[] getStern() {
        return stern;
    }
    public boolean isVertical() {
        return vertical;
    }

    Ship(ShipType type, char[][] boardSquares) {
        askForShipCoordinates(type);
        while (true) {
            readShipCoordinates();
            if (!checkIfStraight()) {
                System.out.print("Error! Wrong ship location! Try again: ");
            } else if (!checkIfCorrectLength(type.getLength())) {
                System.out.printf("Error! Wrong length of the %s! Try again: ", type.getType());
            } else if (!checkIfNotTouchingOtherShips(checkIfVertical(), boardSquares)) {
                System.out.print("Error! You placed it too close to another one. Try again: ");
            } else {
                break;
            }
        }
    }

    private void askForShipCoordinates(ShipType type) {
        System.out.printf("Enter the coordinates of the %s (%d cells): ",
                type.getType(), type.getLength());
    }

    private void readShipCoordinates() {
        String[] coordinates = UserInput.scan().split(" ");
        bow = processCoordinates(coordinates[0]);
        stern = processCoordinates(coordinates[1]);
    }

    private boolean checkIfStraight() {
        return bow[0] == stern[0] || bow[1] == stern[1];
    }

    private boolean checkIfCorrectLength(int length) {
        return Math.abs(bow[0] - stern[0]) == length - 1
                || Math.abs(bow[1] - stern[1]) == length - 1;
    }

    private boolean checkIfVertical() {
        return vertical = bow[0] == stern[0];
    }

    private boolean checkIfNotTouchingOtherShips(boolean vertical, char[][] boardSquares) {
        if (!vertical) {
            for (int i = 0; i < boardSquares.length; i++) {
                if (i >= Math.min(bow[0], stern[0]) &&  i <= Math.max(bow[0], stern[0])) {
                    if (boardSquares[i][bow[1]] == 'O'
                            || (i - 1 >= 0 && boardSquares[i - 1][bow[1]] == 'O')
                            || (i + 1 <= boardSquares.length - 1 && boardSquares[i + 1][bow[1]] == 'O')
                            || (bow[1] - 1 >= 0 && boardSquares[i][bow[1] - 1] == 'O')
                            || (bow[1] + 1 <= boardSquares.length - 1 && boardSquares[i][bow[1] + 1] == 'O')) {
                        return false;
                    }
                }
            }
        } else {
            for (int i = 0; i < boardSquares.length; i++) {
                if (i >= Math.min(bow[1], stern[1]) && i <= Math.max(bow[1], stern[1])) {
                    if (boardSquares[bow[0]][i] == 'O'
                            || (i - 1 >= 0 && boardSquares[bow[0]][i - 1] == 'O')
                            || (i + 1 <= boardSquares.length - 1 && boardSquares[bow[0]][i + 1] == 'O')
                            || (bow[0] - 1 >= 0 && boardSquares[bow[0] - 1][i] == 'O')
                            || (bow[0] + 1 <= boardSquares.length - 1 && boardSquares[bow[0] + 1][i] == 'O')) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
