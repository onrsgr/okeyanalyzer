import numbers.NumberDealer;
import java.util.List;

public class Board {

    private final BoardController boardController = BoardController.getInstance();
    private final NumberDealer numberDealer;
    private List<Integer> numbers;
    private int requiredMovement;
    Player player = new Player();


    public Board(NumberDealer numberDealer, List<Integer> numbers) {
        this.numberDealer = numberDealer;
        this.numbers = numbers;
    }

    public BoardController getBoardController() {
        return boardController;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getRequiredMovement() {
        return requiredMovement;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }


    public void processBoardsAndGetMinimum(){
        Player readyPlayer = prepareBoardBeforeProcess(player);
        this.requiredMovement = findBestMovementCount(readyPlayer);
    }

    private Player prepareBoardBeforeProcess(Player player){

        player.setNumbers(numbers);
        player.setHasFakeOkey(numbers.contains(52));
        player.setHasOkey(numbers.contains(numberDealer.getOkey()));
        player.setHasGosterge(numbers.contains(numberDealer.getGosterge()));
        System.out.println(player);
        return player;
    }

    private int findBestMovementCount(Player player){
        int ciftGitmeCount = boardController.findCiftGidilirse(player, numberDealer.getOkey());
        int seriGitmeCount = boardController.findSeriGidilirse(player, numberDealer.getOkey());
        return Math.min(ciftGitmeCount,seriGitmeCount);
    }
}
