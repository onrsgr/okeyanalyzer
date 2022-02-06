import java.util.*;
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

    public int findSeriGidilirse(Player player, int okey) {
        List<List<Integer>> serialNumberCache = processNumbersDependOnColors(player.getNumbers());
        List<List<Integer>> serialColorCache = processNumbersForRenkli(serialNumberCache);
        List<List<Integer>> allPers = findAllPossibleCombinations(serialNumberCache, serialColorCache, player, okey);
        return findOptimalBoardSelection(allPers, player.getNumbers(),okey);
    }

    // Eldeki numaralara göre taşları dağıtır. Kalan taş sayısını alır.
    private int findOptimalBoardSelection(List<List<Integer>> allPers, List<Integer> numbers, int okey) {
        List<Integer> newNumbers = new ArrayList<>(numbers);
        int okeyCount = getOkeyCount(numbers,okey);
        if (allPers != null) {
            for (List<Integer> subPers : allPers) {
                if (subPers.contains(okey) && okeyCount> 0) {
                    for (Integer numbersInPer : subPers) {
                        newNumbers.remove(numbersInPer);
                    }
                    okeyCount--;
                }
            }
        }
        return newNumbers.size();
    }

    private List<List<Integer>> findAllPossibleCombinations(List<List<Integer>> serialNumberCache, List<List<Integer>> serialColorCache, Player player, int okey) {
        List<List<Integer>> allPers = new ArrayList<>();
        List<List<Integer>> subCacheForSerialNumbers = new ArrayList<>();
        List<Integer> flagForColoredNumbers = new ArrayList(Collections.nCopies(13, 0));
        for (List<Integer> serialNumberControl : serialNumberCache) {
            if (serialNumberCache.size() > 2 && getAllPersSameColor(serialNumberControl, player, okey).size() > 0) {
                subCacheForSerialNumbers.add(getAllPersSameColor(serialNumberControl, player, okey));
            }
        }
        allPers.addAll(subCacheForSerialNumbers);
        for (List<Integer> serialNumberControl : serialColorCache) {
            flagForColoredNumbers = addNewColoredNumbers(getAllNumberCountsSameColors(serialNumberControl), flagForColoredNumbers);
        }
        allPers.addAll(createSimilarColorPer(serialNumberCache, serialColorCache, flagForColoredNumbers));

        return allPers;
    }

    private List<List<Integer>> createSimilarColorPer(List<List<Integer>> serialNumberCache, List<List<Integer>> serialColorCache, List<Integer> subCacheForColoredNumbers) {
        List<List<Integer>> allColoredPer = new ArrayList<>();
        List<Integer> allColoredPerCache = new ArrayList<>();
        int counter = 0;
        for (Integer coloredNumbersFlags : subCacheForColoredNumbers) {
            if (coloredNumbersFlags > 2) {
                int internalIndex = 0;
                for (List<Integer> coloredNumbers : serialColorCache) {
                    if (coloredNumbers.contains(counter)) {
                        allColoredPerCache.add(serialNumberCache.get(internalIndex).get(coloredNumbers.indexOf(counter)));
                    }
                    internalIndex++;
                }
                if (allColoredPerCache.size() > 2) {
                    List<Integer> newList = new ArrayList<>(allColoredPerCache);
                    allColoredPerCache.clear();
                    allColoredPer.add(newList);
                }
            }
            counter++;
        }
        return allColoredPer;
    }

    private List<Integer> getAllNumberCountsSameColors(List<Integer> numbers) {
        ArrayList<Integer> similarCounter = new ArrayList<>(Collections.nCopies(13, 0));
        for (Integer colorNumberFinder : numbers) {
            if (similarCounter.get(colorNumberFinder) == 0) {
                similarCounter.set(colorNumberFinder, similarCounter.get(colorNumberFinder) + 1);
            }
        }
        return similarCounter;
    }

    private List<Integer> addNewColoredNumbers(List<Integer> colorNumbers, List<Integer> colorNumbersCache) {
        for (int i = 0; i < colorNumbers.size(); i++) {
            colorNumbers.set(i, colorNumbers.get(i) + colorNumbersCache.get(i));
        }
        return colorNumbers;
    }

    private List<Integer> getAllPersSameColor(List<Integer> numbers, Player player, int okey) {
        List<Integer> newList = new ArrayList<>(numbers);
        List<Integer> perCache = new ArrayList<>();
        List<Boolean> perFlag = new ArrayList<>();
        for (int i = 0; i < newList.size() - 1; i++) {
            if (newList.get(i) == newList.get(i + 1) - 1) {
                perFlag.add(true);
            } else if ((player.hasFakeOkey() || player.hasOkey()) && newList.size() - i > 2 && newList.get(i) == newList.get(i + 2) - 2) {
                addOkeyOrFakeToPer(okey, numbers, i);
            } else if (perFlag.size() > 1) {
                perCache = (newList.subList(i - perFlag.size(), i + 1));
                perFlag.clear();
            } else {
                perFlag.clear();
            }
        }
        return perCache;
    }

    private void addOkeyOrFakeToPer(int okey, List<Integer> newList, int index) {
        if (newList.get(index) == okey && newList.contains(52)) {
            newList.set(newList.indexOf(52), newList.get(index + 1));
            newList.set(index + 1, 52);
        } else if (newList.contains(okey) && newList.get(index) != okey) {
            newList.set(newList.indexOf(okey), newList.get(index + 1));
            newList.set(index + 1, okey);
        }
    }

    private List<List<Integer>> processNumbersDependOnColors(List<Integer> numbers) {
        Collections.sort(numbers);
        List<List<Integer>> colors = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int colorIndex = i;
            colors.add(numbers.stream()
                    .filter(s -> (s < (colorIndex + 1) * 13) && (colorIndex * 13 < s))
                    .collect(Collectors.toList()));
        }
        return colors;
    }

    private List<List<Integer>> processNumbersForRenkli(List<List<Integer>> colorNumbers) {
        List<List<Integer>> serialCounter = new ArrayList<>();
        for (List<Integer> colors : colorNumbers) {
            serialCounter.add(colors.stream().map(e -> e % 13).collect(Collectors.toList()));
        }
        return serialCounter;
    }

    public int findCiftGidilirse(Player player, int okey) {
        List<Integer> dublicatedList = player.getNumbers().stream().distinct().collect(Collectors.toList());
        int ciftCount = player.getNumbers().size() - dublicatedList.size();
        return (player.getNumbers().size() / 2 - (ciftCount + getOkeyCount(player.getNumbers(), okey))) * 2;
    }

    private Integer getOkeyCount(List<Integer> numbers, int okey) {
        return Collections.frequency(numbers, okey);
    }

}
