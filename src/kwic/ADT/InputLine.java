package kwic.ADT;


import java.util.ArrayList;
import java.util.Arrays;

class InputLine {
    private ArrayList<String> words;
    private int size;

    InputLine(String line) {
        words = new ArrayList<>(Arrays.asList(line.split(" ")));
        size = words.size();
    }

    ArrayList<String> getWords() {
        return words;
    }

    int getSize() {
        return size;
    }
}
