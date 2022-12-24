package aoc_2022.day01;

import aoc_2022.Day;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Day01 extends Day {

    public Day01() {
        super("src/aoc_2022/Day01/day01_input.txt");
    }

    public Day01(String inputFilePath) {
        super(inputFilePath);
    }

    @Override
    public int partOne() {
        try {
            Scanner scanner = new Scanner(inputFile);
            int maxTotalCalories = -1;
            int currentTotal = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("\n") || line.isEmpty()) {
                    if (currentTotal > maxTotalCalories) {
                        maxTotalCalories = currentTotal;
                    }
                    currentTotal = 0;
                } else {
                    currentTotal += Integer.parseInt(line);
                }
            }
            if (currentTotal > maxTotalCalories) {
                maxTotalCalories = currentTotal;
            }
            return maxTotalCalories;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int partTwo() {
        try {
            Scanner scanner = new Scanner(inputFile);
            int[] maxTotalCalories = {-1, -1, -1};
            int currentTotal = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("\n") || line.isEmpty()) {
                    for (int i = 0; i < maxTotalCalories.length; i++) {
                        if (currentTotal > maxTotalCalories[i]) {
                            int temp = maxTotalCalories[i];
                            maxTotalCalories[i] = currentTotal;
                            currentTotal = temp;
                        }
                    }
                    currentTotal = 0;
                } else {
                    currentTotal += Integer.parseInt(line);
                }
            }
            for (int i = 0; i < maxTotalCalories.length; i++) {
                if (currentTotal > maxTotalCalories[i]) {
                    int temp = maxTotalCalories[i];
                    maxTotalCalories[i] = currentTotal;
                    currentTotal = temp;
                }
            }
            return IntStream.of(maxTotalCalories).sum();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
