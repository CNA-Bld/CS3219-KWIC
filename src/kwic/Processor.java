package kwic;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Processor {
    public List<String> process(Collection<String> inputLines, Set<String> stopWords);
}
