package kwic.ADT;

import kwic.Processor;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ADTProcessor implements Processor {
    @Override
    public List<String> process(Collection<String> inputLines, Set<String> stopWords) {
        Set<CircularLine> circularLineSet = new HashSet<>();
        inputLines.forEach(line -> circularLineSet.add(new CircularLine(new InputLine(line, stopWords), stopWords)));
        AlphabeticalList alphabeticalList = new AlphabeticalList(circularLineSet);
        return alphabeticalList.getList();
    }
}
