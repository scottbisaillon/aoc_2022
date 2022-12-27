package aoc_2022.day03;

import aoc_2022.Day;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Day03 extends Day<Integer, Integer> {

    public Day03() {
        super("src/aoc_2022/Day03/day03_input.txt");
    }

    public Day03(String inputFilePath) {
        super(inputFilePath);
    }

    @Override
    public Integer partOne() {
        try {
            Scanner scanner = new Scanner(inputFile);

            int prioritySum = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                char[] items = line.toCharArray();
                Set<Character> compartmentOne = new HashSet<>();
                for (int i = 0; i < items.length; i++) {
                    if (i < items.length / 2) {
                        compartmentOne.add(items[i]);
                    } else {
                        if (compartmentOne.contains(items[i])) {
                            prioritySum += getPriority(items[i]);
                            break;
                        }
                    }
                }
            }
            return prioritySum;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer partTwo() {
        try {
            Scanner scanner = new Scanner(inputFile);

            int prioritySum = 0;
            while (scanner.hasNextLine()) {
                Set<Character> bagOneItems = scanner.nextLine()
                        .chars()
                        .mapToObj(value -> (char) value)
                        .collect(Collectors.toSet());
                Set<Character> bagTwoItems = new HashSet<>();

                char[] bagTwo = scanner.nextLine().toCharArray();
                char[] bagThree = scanner.nextLine().toCharArray();

                for (char c : bagTwo) {
                    if (bagOneItems.contains(c)) {
                        bagTwoItems.add(c);
                    }
                }
                for (char c : bagThree) {
                    if (bagTwoItems.contains(c)) {
                        prioritySum += getPriority(c);
                        break;
                    }
                }
            }
            return prioritySum;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    int getPriority(char item) {
        if (Character.isUpperCase(item)) {
            return ((int) item) - 38;
        } else {  // (Character.isLowerCase(item))
            return ((int) item) - 96;
        }
    }
}
