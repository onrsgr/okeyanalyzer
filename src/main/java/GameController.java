import numbers.NumberDealer;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    NumberDealer numberDealer;
    List<Board> boards = new ArrayList<>();
    int starterPlayerIndex;
    {
        numberDealer = new NumberDealer();
        numberDealer.prepareNewHand();

        for (int i = 0; i < 4; i++) {
            Board newBoard = new Board(numberDealer,(numberDealer.getPreparedBoards().get(i)));
            boards.add(newBoard);
        }
    }
    public void newGame() {
        numberDealer.prepareNewHand();
        distributeNumbersToBoards(this.boards, this.numberDealer, this.starterPlayerIndex);
        boards.forEach(control -> control.processBoardsAndGetMinimum());

    }

    private void distributeNumbersToBoards(List<Board> boards, NumberDealer numberDealer, int starterPlayerIndex) {
        for (int i = 0; i < boards.size(); i++) {
            boards.get(i).setNumbers(numberDealer.getPreparedBoards().get(i));
        }
    }

}
