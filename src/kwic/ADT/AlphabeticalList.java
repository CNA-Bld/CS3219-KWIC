package kwic.ADT;

import java.util.*;

class AlphabeticalList {
    List<String> alphabeticalList;

    AlphabeticalList(Set<CircularLine> circularLineSet) {
        alphabeticalList = new ArrayList<>();
        Set<String> completeSet = new HashSet<>();
        circularLineSet.forEach(set -> completeSet.addAll(set.getSet()));
        alphabeticalList.addAll(completeSet);
        Collections.sort(alphabeticalList);
    }

    List<String> getList() {
        return alphabeticalList;
    }
}
