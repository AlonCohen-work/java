//sindi koifan 211657481 alon cohen 319039707

package xo;

import java.util.Random;

import XO.Game.Mark;

public class SelfPlayer extends Player {
    protected final Game game;

    public SelfPlayer(Mark current, Game game) {
        super(current, game);
        this.game = game;
    }

    public synchronized void makeMove() {
        Cordinate[] cells = game.getFreeCells();
        if (cells.length > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(cells.length);

            Cordinate randomNumber = cells[randomIndex];
            int row = randomNumber.i;
            int col = randomNumber.j;

            game.putMark(row, col, current);
            game.printBoard();

            try {
                Thread.sleep(500); // Simulate some processing time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        makeMove();
        notifyAll();
    }

    private synchronized void waitForGame() {
        try {
            game.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
