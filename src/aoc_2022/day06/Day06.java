package aoc_2022.day06;

import aoc_2022.Day;

import java.io.FileNotFoundException;
import java.util.*;

public class Day06 extends Day<Integer, Integer> {
    public Day06() {
        super("src/aoc_2022/day06/day06_input.txt");
    }

    @Override
    public Integer partOne() {
        try {
            Scanner scanner = new Scanner(inputFile);

            String input = scanner.nextLine();

            Queue<Character> window = new ArrayDeque<>(4);

            int result = 0;
            for (int i = 0; i < input.length(); i++) {
                if (i < 4) {
                    window.add(input.charAt(i));
                }
                else {
                    if (containsUniqueCharacters(window)) {
                        result = i;
                        break;
                    }
                    window.remove();
                    window.add(input.charAt(i));
                }
            }

            return result;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer partTwo() {
        try {
            Scanner scanner = new Scanner(inputFile);

            String input = scanner.nextLine();

            Queue<Character> window = new ArrayDeque<>(14);

            int result = 0;
            for (int i = 0; i < input.length(); i++) {
                if (i < 14) {
                    window.add(input.charAt(i));
                }
                else {
                    if (containsUniqueCharacters(window)) {
                        result = i;
                        break;
                    }
                    window.remove();
                    window.add(input.charAt(i));
                }
            }

            return result;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    boolean containsUniqueCharacters(Queue<Character> window) {
        Set<Character> unique = new HashSet<>(window.stream().toList());
        return unique.size() == window.size();
    }
}
