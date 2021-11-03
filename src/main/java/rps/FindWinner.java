package rps;

import java.util.Arrays;
import java.util.List;

public class FindWinner {
    private List<String> signs;
    private int size;

    public FindWinner(String[] signs) {
        this.signs = Arrays.asList(signs);
        this.size = this.signs.size();
    }

    public Result getResult(String playerMove, String computerMove) {
        //L
        int playerIndex = signs.indexOf(playerMove);
        int computerIndex = signs.indexOf(computerMove);
        if (playerIndex == computerIndex) {
            return Result.DRAW;
        }
        int i = 1;
        while (i < (size / 2)+1) {
            if ((playerIndex + i) % size == computerIndex) {
                return Result.LOSE;
            }
            i++;
        }
        return Result.WIN;
    }

    enum Result {
        WIN,
        LOSE,
        DRAW
    }
}

