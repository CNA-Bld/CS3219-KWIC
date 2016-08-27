package kwic.ADT;

import kwic.Processor;

import java.util.*;

public class ADTProcessor implements Processor{

    class InputLine{
        ArrayList<String> words;
        int size;

        private InputLine(String line) {
            words = new ArrayList<>(Arrays.asList(line.split(" ")));
            size = words.size();
        }
        private ArrayList<String> getWords(){
            return words;
        }
        private int getSize(){
            return size;
        }
    }

    class CircularLine {
        Set<String> circularSet;

        private CircularLine(InputLine inputLine, Set<String> stopWords){
            circularSet = new HashSet<>();
            for (int index=0; index<inputLine.getSize(); index++) {
                if (!stopWords.contains(inputLine.getWords().get(index))) {
                    List<String> arr = inputLine.getWords().subList(index, inputLine.getSize());
                    if (index != 0) arr.addAll(inputLine.getWords().subList(0, index));
                    circularSet.add(String.join(" ", arr));
                }
            }
        }
        private Set<String> getSet(){
            return circularSet;
        }
    }

    class AlphabeticalList {
        List<String> alphabeticalList;

        private AlphabeticalList(Set<CircularLine> circularLineSet) {
            alphabeticalList = new ArrayList<>();
            Set<String> completeSet = new HashSet<>();
            circularLineSet.forEach(set -> completeSet.addAll(set.getSet()));
            alphabeticalList.addAll(completeSet);
            Collections.sort(alphabeticalList);
        }
        private List<String> getList(){
            return alphabeticalList;
        }

    }

    @Override
    public List<String> process(Collection<String> inputLines, Set<String> stopWords){
        Set<CircularLine> circularLineSet = new HashSet<>();
        inputLines.forEach(line -> circularLineSet.add(new CircularLine(new InputLine(line), stopWords)));
        AlphabeticalList alphabeticalList = new AlphabeticalList(circularLineSet);
        return alphabeticalList.getList();

    }

}
