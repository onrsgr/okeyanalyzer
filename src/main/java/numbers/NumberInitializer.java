package numbers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NumberInitializer {

    private static List<Integer> gameNumbers;

    // Okeyde Kullanılacak Taşların Hazırlanması
    private static List<Integer> getNewNumbers(){
        gameNumbers = Stream.iterate(0, n -> n + 1)
                .limit(52)
                .collect(Collectors.toList());
        gameNumbers.addAll(gameNumbers);
        return gameNumbers;
    }

    // Kartların Karıştırılması
    public static List<Integer> getPreparedNumbers(){
        List<Integer> newBoard = getNewNumbers();
        Collections.shuffle(newBoard);
        return newBoard;
    }
}
