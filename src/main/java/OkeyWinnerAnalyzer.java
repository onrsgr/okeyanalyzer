public class OkeyWinnerAnalyzer {


    public static void main(String[] args) {

        System.out.println("Hello Digitoy Games!");

        GameController gameController = new GameController();

        while (true) {
            gameController.newGame();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}