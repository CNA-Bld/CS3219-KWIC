package kwic.ADT;


import java.util.ArrayList;
import java.util.Arrays;

public class InputLine {
    ArrayList<String> words;
    int size;

    InputLine(String line) {
        words = new ArrayList<>(Arrays.asList(line.split(" ")));
        size = words.size();
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public int getSize() {
        return size;
    }
}
