package kwic.ADT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


class CircularLine {
    private Set<String> circularSet;

    CircularLine(InputLine inputLine, Set<String> stopWords) {
        circularSet = new HashSet<>();
        for (int index = 0; index < inputLine.getSize(); index++) {
            if (!stopWords.contains(inputLine.getWords().get(index))) {
                List<String> arr = new ArrayList<>();
                arr.addAll(inputLine.getWords().subList(index, inputLine.getSize()));
                if (index != 0) arr.addAll(inputLine.getWords().subList(0, index));
                String str = String.join(" ", arr);
                circularSet.add(str.substring(0,1).toUpperCase().concat(str.substring(1)));
            }
        }
    }

    Set<String> getSet() {
        return circularSet;
    }
} 
