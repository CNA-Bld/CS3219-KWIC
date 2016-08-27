package kwic;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class DummyProcessor implements Processor {
    @Override
    public List<String> process(Collection<String> inputLines, Set<String> stopWords) {
        return Collections.emptyList();
    }
}
