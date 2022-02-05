import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
        int ciftCount = player.getNumbers().size()-dublicatedList.size();
        int okeyCount = Collections.frequency(player.getNumbers(), okey);
        return (player.getNumbers().size()/2 - (ciftCount + okeyCount));
    }
}
