import java.util.List;

public class Player {

    private List<Integer> numbers;
    private boolean hasJoker;
    private boolean hasGosterge;
    private boolean hasFakeJoker;

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public boolean isHasJoker() {
        return hasJoker;
    }

    public void setHasJoker(boolean hasJoker) {
        this.hasJoker = hasJoker;
    }

    public boolean isHasGosterge() {
        return hasGosterge;
    }

    public void setHasGosterge(boolean hasGosterge) {
        this.hasGosterge = hasGosterge;
    }

    public boolean isHasFakeJoker() {
        return hasFakeJoker;
    }

    public void setHasFakeJoker(boolean hasFakeJoker) {
        this.hasFakeJoker = hasFakeJoker;
    }

    @Override
    public String toString() {
        return "Player{" +
                "numbers=" + numbers +
                ", hasJoker=" + hasJoker +
                ", hasGosterge=" + hasGosterge +
                ", hasFakeJoker=" + hasFakeJoker +
                '}';
    }
}
