package kwic.ADT;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CircularLine {
    Set<String> circularSet;

    public CircularLine(InputLine inputLine, Set<String> stopWords) {
        circularSet = new HashSet<>();
        for (int index = 0; index < inputLine.getSize(); index++) {
            if (!stopWords.contains(inputLine.getWords().get(index))) {
                List<String> arr = inputLine.getWords().subList(index, inputLine.getSize());
                if (index != 0) arr.addAll(inputLine.getWords().subList(0, index));
                circularSet.add(String.join(" ", arr));
            }
        }
    }

    public Set<String> getSet() {
        return circularSet;
    }
} 
