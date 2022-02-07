import numbers.NumberDealer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GameController {

    NumberDealer numberDealer;
    List<Board> boards = new ArrayList<>();
    int starterPlayerIndex;

    {
        numberDealer = new NumberDealer();
        numberDealer.prepareNewHand();

        for (int i = 0; i < 4; i++) {
            Board newBoard = new Board(numberDealer, (numberDealer.getPreparedBoards().get(i)));
            boards.add(newBoard);
        }
    }

    // Oyunun kontrol edildiği kısım
    public void newGame() {
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Hello Welcome to New Okey Game");
            System.out.println("Please Press 1 to New Game");
            System.out.println("If you want to exit, Please press 0");
            System.out.println("*********");
            System.out.println("Selection : ");
            int set = scan.nextInt();
            System.out.println("*********");


            if (set == 1) {
                int starter = 0;
                while ((starter < 1 || starter > 4)) {
                    System.out.println("Select Starter Player 1 to 4 : ");
                    starter = scan.nextInt();
                }
                this.starterPlayerIndex = starter;
                ArrayList<Integer> boardResults = new ArrayList<>();
                numberDealer.prepareNewHand();
                distributeNumbersToBoards(this.boards, this.numberDealer, this.starterPlayerIndex);
                boards.forEach(control -> control.processBoardsAndGetMinimum());
                boards.forEach(printer-> System.out.println("Boards of players : " + printer.getNumbers()));
                for(int i = 0; i<4 ; i++){
                    boardResults.add(boards.get(i).getRequiredMovement());
                }
                winnerFinder(boardResults);
            } else if (set == 0) {
                System.exit(0);
            }
        }
    }

    // Kazananın açıklandığı bölüm
    private void winnerFinder(ArrayList<Integer> lists){
        List<Integer> controller = new ArrayList<>(lists);
        Collections.sort(controller);
        int minMovement = controller.get(0);
        for(int i = 0; i<lists.size(); i++){
            if(lists.get(i) == minMovement){
                System.out.println("Player " + (i+1) + " has higher chance to win");
            }
        }
    }

    // Taşların oyunculara göre dağıtımının yapıldığı yer
    private void distributeNumbersToBoards(List<Board> boards, NumberDealer numberDealer, int starterPlayerIndex) {
        boards.get(starterPlayerIndex-1).setNumbers(numberDealer.getPreparedBoards().get(0));
        int counter = 1;
        for (int i = 0; i < boards.size(); i++) {
            if (i != starterPlayerIndex-1) {
                boards.get(i).setNumbers(numberDealer.getPreparedBoards().get(counter));
                counter++;
            }
        }
    }
}
