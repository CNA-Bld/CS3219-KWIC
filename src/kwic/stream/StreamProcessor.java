package kwic.stream;

import kwic.Processor;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamProcessor implements Processor {
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

    @Override
    public List<String> process(Collection<String> inputLines, Set<String> stopWords) {
        return inputLines.parallelStream()
                .map(line -> Stream.of(line.split(" +"))
                        .map(word -> stopWords.contains(word.toLowerCase()) ? word.toLowerCase() : word)
                        .collect(Collectors.toList()))
                .flatMap(words ->
                        IntStream.range(0, words.size()).boxed()
                                .filter(startIndex -> !stopWords.contains(words.get(startIndex)))
                                .map(startIndex -> reconstructString(words, startIndex))
                )
                .sorted()
                .collect(Collectors.toList());
    }
}
