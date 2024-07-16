//sindi koifan 211657481 alon cohen 319039707

package xo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the game type: 1 - Self Game, 2 - User Game");
        int choice = scanner.nextInt();

        Game game;
        if (choice == 1) {
            game = new SelfGame();
        } else if (choice == 2) {
            game = new UserGame();
        } else {
            System.out.println("Invalid choice. Exiting.");
            return;
        }

        game.playGame();
    }
}
