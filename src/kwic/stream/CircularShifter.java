package kwic.stream;

import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class CircularShifter {
    private Set<String> stopWords;

    CircularShifter(Set<String> stopWords) {
        this.stopWords = stopWords;
    }

    private static String reconstructString(List<String> words, int start) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (int i = start; i < words.size(); i++) {
            stringJoiner.add(words.get(i));
        }
        for (int i = 0; i < start; i++) {
            stringJoiner.add(words.get(i));
        }
        return stringJoiner.toString();
    }

    Stream<String> circularShift(List<String> words) {
        return IntStream.range(0, words.size()).boxed()
                .filter(startIndex -> !stopWords.contains(words.get(startIndex)))
                .map(startIndex -> reconstructString(words, startIndex))
                .map(keyword -> keyword.substring(0, 1).toUpperCase().concat(keyword.substring(1)));
    }
}
