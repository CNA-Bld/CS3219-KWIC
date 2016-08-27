package kwic;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.*;

public class Launcher {
    private static final String DEFAULT_PROCESSOR = "kwic.stream.StreamProcessor";
    private static final Set<String> DEFAULT_STOP_WORDS = new HashSet<>(Arrays.asList("is", "the", "of", "and", "as", "a", "after"));

    private static final String FLAG_STOP_WORDS_FILENAME = "stopwords";
    private static final String FLAG_PROCESSOR_CLASS = "processor";

    private static final String INFO_STOP_WORDS_NOT_PROVIDED = "Stop Words file not provided, using default value.";
    private static final String INFO_STOP_WORDS_IOEXCEPTION = "IOException when reading Stop Words file, using default value.";
    private static final String INFO_PROCESSOR_REFLECTION_FAILED = "Failed to initialize processor.";

    private Set<String> stopWords;
    private Processor processor;

    public Launcher() {
        String stopWordsFile = System.getProperty(FLAG_STOP_WORDS_FILENAME);
        if (stopWordsFile == null) {
            System.err.println(INFO_STOP_WORDS_NOT_PROVIDED);
            stopWords = DEFAULT_STOP_WORDS;
        } else {
            try {
                stopWords = new HashSet<>(Files.readAllLines(FileSystems.getDefault().getPath(stopWordsFile)));
            } catch (IOException e) {
                System.err.println(INFO_STOP_WORDS_IOEXCEPTION);
                stopWords = DEFAULT_STOP_WORDS;
            }
        }

        String processorClazz = System.getProperty(FLAG_PROCESSOR_CLASS, DEFAULT_PROCESSOR);
        try {
            processor = (Processor) Class.forName(processorClazz).getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            System.err.println(INFO_PROCESSOR_REFLECTION_FAILED);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.execute();
    }

    public void execute() {
        LinkedList<String> inputLines = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            if (!nextLine.isEmpty()) {
                inputLines.add(nextLine);
            }
        }

        processor.process(inputLines, stopWords).forEach(System.out::println);
    }
}
