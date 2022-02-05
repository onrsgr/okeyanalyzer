import numbers.NumberDealer;

public class OkeyWinnerAnalyzer {


    public static void main(String[] args) {

        System.out.println("Hello Digitoy Games!");

        NumberDealer nd = new NumberDealer();

        while (true) {
            nd.prepareNewHand();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}