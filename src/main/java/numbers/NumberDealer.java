package numbers;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NumberDealer {

    int okey;
    int gosterge;
    List<Integer> numbersBeforeDeal;
    List<List<Integer>> preparedBoards;

    public int getOkey() {
        return okey;
    }

    public int getGosterge() {
        return gosterge;
    }

    public List<Integer> getNumbersBeforeDeal() {
        return numbersBeforeDeal;
    }

    public List<List<Integer>> getPreparedBoards() {
        return preparedBoards;
    }

    // Yeni Elin Dağıtılması
    public void prepareNewHand() {
        this.numbersBeforeDeal = NumberInitializer.getPreparedNumbers();
        this.gosterge = setNewGosterge(getNumbersBeforeDeal());
        this.okey = setOkeyFromGosterge(getGosterge());
        this.preparedBoards = dealNewHand(getNumbersBeforeDeal());

    }

    // Random Gösterge Seçilip Listeden Çıkartılması
    private int setNewGosterge(List<Integer> numbers) {
        int gostergeLocation = (int) (Math.random() * ((numbers.size() / 2) - 1));
        int gosterge = numbers.get(gostergeLocation);
        numbers.remove(gostergeLocation);
        return gosterge;
    }

    // Gösterden Okey Üretilmesinin Sağlandığı Bölüm
    private int setOkeyFromGosterge(int gosterge) {
        if (gosterge % 12 == 0 && gosterge != 0) {
            return (gosterge - 12);
        }else {
            return (gosterge + 1);
        }
    }

    // Taşların Dağıtılması Methotu
    private List<List<Integer>> dealNewHand(List<Integer> numbersBeforeDeal){
        List<List<Integer>> starterBoard = new ArrayList<>();
        starterBoard.add(numbersBeforeDeal.subList(0,15));
        List<List<Integer>> nonStarterBoards = Lists.partition(numbersBeforeDeal.subList(15,57),14);
        List<List<Integer>> playerBoards = Stream.concat(starterBoard.stream(), nonStarterBoards.stream())
                .collect(Collectors.toList());
        return playerBoards;
    }


}
