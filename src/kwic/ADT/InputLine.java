package kwic.ADT;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

class InputLine {
    private ArrayList<String> words;

    InputLine(String line, Set<String> stopWords) {
        words = new ArrayList<>();
        for (String word : line.split(" ")) {
            if (stopWords.contains(word.toLowerCase())) {
                words.add(word.toLowerCase());
            } else {
                words.add(word);
            }
        }
    }

    ArrayList<String> getWords() {
        return words;
    }

    int getSize() {
        return words.size();
    }
}
