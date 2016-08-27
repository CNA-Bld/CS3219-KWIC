package kwic.stream;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Tokenizer {
    private Set<String> stopWords;

    Tokenizer(Set<String> stopWords) {
        this.stopWords = stopWords;
    }

    List<String> tokenize(String line) {
        return Stream.of(line.split(" +"))
                .map(word -> stopWords.contains(word.toLowerCase()) ? word.toLowerCase() : word)
                .collect(Collectors.toList());
    }
}
