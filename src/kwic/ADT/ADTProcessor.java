package kwic.ADT;

import kwic.Processor;

import java.util.*;

public class ADTProcessor implements Processor {

    @Override
    public List<String> process(Collection<String> inputLines, Set<String> stopWords) {
        Set<CircularLine> circularLineSet = new HashSet<>();
        inputLines.forEach(line -> circularLineSet.add(new CircularLine(new InputLine(line), stopWords)));
        AlphabeticalList alphabeticalList = new AlphabeticalList(circularLineSet);
        return alphabeticalList.getList();

    }

}
