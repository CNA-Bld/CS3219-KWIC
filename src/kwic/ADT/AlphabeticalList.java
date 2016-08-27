package kwic.ADT;

import java.util.*;

public class AlphabeticalList {

    List<String> alphabeticalList;

    public AlphabeticalList(Set<CircularLine> circularLineSet) {
        alphabeticalList = new ArrayList<>();
        Set<String> completeSet = new HashSet<>();
        circularLineSet.forEach(set -> completeSet.addAll(set.getSet()));
        alphabeticalList.addAll(completeSet);
        Collections.sort(alphabeticalList);
    }

    public List<String> getList() {
        return alphabeticalList;
    }


}
