package day03;

import day02.Day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Day03 {
    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    static void partOne() {
        File inputFile = new File("src/Day03/day03_input.txt");
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
            System.out.println(prioritySum);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static void partTwo() {
        File inputFile = new File("src/Day03/day03_input.txt");
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
            System.out.println(prioritySum);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static int getPriority(char item) {
        if (Character.isUpperCase(item)) {
            return ((int) item) - 38;
        } else {  // (Character.isLowerCase(item))
            return ((int) item) - 96;
        }
    }
}
