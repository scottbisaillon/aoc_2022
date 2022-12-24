package aoc_2022;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public abstract class Day {

    final String inputFilePath;
    public final File inputFile;

    protected Day(String inputFilePath) {
        this.inputFilePath = inputFilePath;
        this.inputFile = new File(inputFilePath);
    }

    public void solve() {
        System.out.printf("-- %s --%n", this.getClass().getSimpleName());
        System.out.printf("Part One Solution: %s%n", partOne());
        System.out.printf("Part Two Solution: %s%n", partTwo());
        System.out.println();
    }

    abstract public int partOne();

    abstract public int partTwo();

    public Stream<String> getLinesStream() {
        try (Stream<String> stream = Files.lines(inputFile.toPath())) {
            return stream;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
