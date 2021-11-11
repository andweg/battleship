package battleship;

public class Game implements CoordinatesProcessor {

    private int currentPlayer = 1;

    PlayerBoard p1PlayerBoard;
    PlayerBoard p2PlayerBoard;
    TargetBoard p1TargetBoard;
    TargetBoard p2TargetBoard;

    public Game() {
        this.p1PlayerBoard = new PlayerBoard(1);
        this.p2PlayerBoard = new PlayerBoard(2);
        this.p1TargetBoard = new TargetBoard(p2PlayerBoard);
        this.p2TargetBoard = new TargetBoard(p1PlayerBoard);
        battle();
    }

    private void battle() {
        while (true) {
            displayCurrentBoards();
            System.out.printf("Player %d, it's your turn: ", currentPlayer);
            try {
                shootAndCheckIfValidTarget();
            } catch (Exception e) {
                System.out.println("Error! You entered the wrong coordinates!");
                continue;
            }
            currentPlayer = currentPlayer % 2 + 1;
            UserInput.enterPrompt();
        }
    }

    private void displayCurrentBoards() {
        if (currentPlayer == 1) {
            p1TargetBoard.printBoard();
            System.out.println("---------------------");
            p1PlayerBoard.printBoard();
        } else {
            p2TargetBoard.printBoard();
            System.out.println("---------------------");
            p2PlayerBoard.printBoard();
        }
    }

    private void shootAndCheckIfValidTarget() {
        while (true) {
            int[] target = processCoordinates(UserInput.scan());
            try {
                if (currentPlayer == 1) {
                    p1TargetBoard.addTarget(target, p2PlayerBoard.checkHit(target));
                } else {
                    p2TargetBoard.addTarget(target, p1PlayerBoard.checkHit(target));
                }
                break;
            } catch (Exception e) {
                System.out.println("Error! You entered the wrong coordinates! Try again: ");
            }
        }
    }

}
