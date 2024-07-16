//sindi koifan 211657481 alon cohen 319039707

package xo;

import xo.Game.PlayerType;

public abstract class Player {
    protected PlayerType playerType;

    public Player(PlayerType playerType) {
        this.playerType = playerType;
    }

    public abstract void makeMove(Game game);

    public PlayerType getPlayerType() {
        return playerType;
    }
}