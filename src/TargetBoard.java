package battleship;

public class TargetBoard extends Board {

    PlayerBoard opponentPlayerBoard;

    TargetBoard(PlayerBoard opponentPlayerBoard) {
        super(opponentPlayerBoard.player);
        this.opponentPlayerBoard = opponentPlayerBoard;
    }

    void addTarget (int[] target, char result) {
        if (boardSquares[target[0]][target[1]] == '~') {
            boardSquares[target[0]][target[1]] = result;
        }
    }

}
