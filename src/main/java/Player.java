import java.util.List;

public class Player {

    private List<Integer> numbers;
    private boolean hasOkey;
    private boolean hasGosterge;
    private boolean hasFakeOkey;

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public boolean hasOkey() {
        return hasOkey;
    }

    public void setHasOkey(boolean hasOkey) {
        this.hasOkey = hasOkey;
    }

    public boolean hasGosterge() {
        return hasGosterge;
    }

    public void setHasGosterge(boolean hasGosterge) {
        this.hasGosterge = hasGosterge;
    }

    public boolean hasFakeOkey() {
        return hasFakeOkey;
    }

    public void setHasFakeOkey(boolean hasFakeOkey) {
        this.hasFakeOkey = hasFakeOkey;
    }

    @Override
    public String toString() {
        return "Player{" +
                "numbers=" + numbers +
                ", hasJoker=" + hasOkey +
                ", hasGosterge=" + hasGosterge +
                ", hasFakeJoker=" + hasFakeOkey +
                '}';
    }
}
