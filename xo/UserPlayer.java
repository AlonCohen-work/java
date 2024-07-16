//sindi koifan 211657481 alon cohen 319039707

package xo;

import java.util.Scanner;

import xo.Game.PlayerType;

public class UserPlayer extends Player {
    private Scanner scanner;

    public UserPlayer(PlayerType playerType) {
        super(playerType);
        scanner = new Scanner(System.in);
    }

    @Override
    public void makeMove(Game game) {
        int[][] freeCells = game.getFreeCells();
        if (freeCells.length > 0) {
            System.out.println("Enter row and column (separated by space):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (isValidMove(row, col, game)) {
            	game.getGameBoard()[row][col] = (playerType == PlayerType.X) ? 'X' : 'O';
                game.printBoard();
            } else {
                System.out.println("Invalid move. Try again.");
                makeMove(game);
            }
        }
    }

    private boolean isValidMove(int row, int col, Game game) {
        char[][] board = game.getGameBoard();
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length && board[row][col] == '-';
    }
}
