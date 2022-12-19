package day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Day01 {

    static void partOne() {
        File inputFile = new File("src/Day01/day01_input.txt");
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
            System.out.println(maxTotalCalories);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static void partTwo() {
        File inputFile = new File("src/Day01/day01_input.txt");
        try {
            Scanner scanner = new Scanner(inputFile);
            int maxTotalCalories[] = {-1, -1, -1};
            int currentTotal = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("\n") || line.isEmpty()) {
                    for (int i = 0; i < maxTotalCalories.length; i++) {
                        if (currentTotal > maxTotalCalories[i]) {
                            maxTotalCalories[i] = currentTotal;
                            break;
                        }
                    }
                    currentTotal = 0;
                } else {
                    currentTotal += Integer.parseInt(line);
                }
            }
            for (int i = 0; i < maxTotalCalories.length; i++) {
                if (currentTotal > maxTotalCalories[i]) {
                    maxTotalCalories[i] = currentTotal;
                    break;
                }
            }
            int topThreeTotal = IntStream.of(maxTotalCalories).sum();
            System.out.println(topThreeTotal);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        partOne();
        partTwo();
    }
}
