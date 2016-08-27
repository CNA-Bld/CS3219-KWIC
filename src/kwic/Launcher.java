package kwic;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.*;

public class Launcher {

    private static final Set<String> DEFAULT_STOP_WORDS = new HashSet<>(Arrays.asList("is", "the", "of", "and", "as", "a", "after"));

    public static void main(String[] args) {
        Set<String> stopWords;

        if (args.length < 1) {
            System.err.println("Stop Words file not provided, using default value.");
            stopWords = DEFAULT_STOP_WORDS;
        } else {
            String filename = args[0];
            try {
                stopWords = new HashSet<>(Files.readAllLines(FileSystems.getDefault().getPath(filename)));
            } catch (IOException e) {
                System.err.println("IOException when reading Stop Words file, using default value.");
                stopWords = DEFAULT_STOP_WORDS;
            }
        }

        LinkedList<String> inputLines = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            if (!nextLine.isEmpty()) {
                inputLines.add(nextLine);
            }
        }

        Processor processor = new DummyProcessor();

        processor.process(inputLines, stopWords).forEach(System.out::println);
    }
}
