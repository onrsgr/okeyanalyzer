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
        int gostergeLocation = (int) (Math.random() * (numbers.size()));
        int gosterge = numbers.get(gostergeLocation);
        if(gosterge == 52){
            gostergeLocation = replaceGostergeIfFakeOkey(numbers);
            numbers.remove(gostergeLocation);
            return numbers.get(gostergeLocation-1);
        }
        numbers.remove(gostergeLocation);
        return gosterge;
    }

    private int replaceGostergeIfFakeOkey(List<Integer> numbers){
        int index = numbers.size();
        while(true){
            if(numbers.get(index-1)!=52){
                return index-1;
            }
        }
    }

    // Gösterden Okey Üretilmesinin Sağlandığı Bölüm
    private int setOkeyFromGosterge(int gosterge) {
        if ((gosterge + 1)%13 == 0 && gosterge != 0) {
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
