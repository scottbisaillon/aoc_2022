package aoc_2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.stream.Stream;

public abstract class Day<T, V> {

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

    abstract public T partOne();

    abstract public V partTwo();

    public Scanner getFileScanner() {
        try {
            return new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        }
    }

    public Stream<String> getLinesStream() {
        try {
            return Files.lines(inputFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
