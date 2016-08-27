package kwic.stream;

import kwic.Processor;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamProcessor implements Processor {
    @Override
    public List<String> process(Collection<String> inputLines, Set<String> stopWords) {
        Tokenizer tokenizer = new Tokenizer(stopWords);
        CircularShifter circularShifter = new CircularShifter(stopWords);

        return inputLines.parallelStream()
                .map(tokenizer::tokenize)
                .flatMap(circularShifter::circularShift)
                .sorted()
                .collect(Collectors.toList());
    }
}
