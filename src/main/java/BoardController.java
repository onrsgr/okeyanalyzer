import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BoardController {

    private static BoardController boardController;

    private BoardController() {
    }

    public static synchronized BoardController getInstance() {
        if (boardController == null) {
            boardController = new BoardController();
        }
        return boardController;
    }


    public int findSeriGidilirse(Player player) {
        return 0;
    }

    public int findCiftGidilirse(Player player, int okey) {
        List<Integer> dublicatedList = player.getNumbers().stream().distinct().collect(Collectors.toList());
        int okeyCount = Collections.frequency(player.getNumbers(), okey);
        return controlJokerForCiftGidilirse(okeyCount, dublicatedList.size())/2;
    }

    private int controlJokerForCiftGidilirse(int okeySayisi, int ciftSayisi) {
        if (okeySayisi > 0) {
            return ciftSayisi + 1;
        } else if (okeySayisi == 0) {
            return ciftSayisi;
        } else {
            return 0;
        }
    }

}
