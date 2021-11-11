package battleship;

import java.util.Scanner;

public class UserInput {

    private static final Scanner scanner = new Scanner(System.in);

    static String scan() {
        return scanner.nextLine();
    }

    static String enterPrompt() {
        System.out.println("Press Enter and pass the move to another player");
        return scan();
    }
}
