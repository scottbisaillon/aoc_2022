package aoc_2022.day05;

import aoc_2022.Day;

import java.io.FileNotFoundException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day05 extends Day<String, String> {

    final String regex = "move (\\d+) from (\\d+) to (\\d+)$";
    final Pattern pattern = Pattern.compile(regex);

    public Day05() {
        super("src/aoc_2022/day05/day05_input.txt");
    }

    protected Day05(String inputFilePath) {
        super(inputFilePath);
    }

    @Override
    public String partOne() {
        List<Stack<Character>> stacks = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            stacks.add(i, new Stack<>());
        }
        List<Character> crates = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("1")) {
                    scanner.nextLine();
                    break;
                }
                CharacterIterator it = new StringCharacterIterator(line);
                while (it.current() != CharacterIterator.DONE) {
                    char crate = it.next();
                    it.next();
                    it.next();
                    it.next();
                    if (Character.isAlphabetic(crate)) {
                        crates.add(crate);
                    } else {
                        crates.add(null);
                    }
                }
            }
            for (int i = crates.size() - 1; i >= 0; i--) {
                if (crates.get(i) != null) {
                    stacks.get(i % 9).push(crates.get(i));
                }
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                final Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    int numberOfCrates = Integer.parseInt(matcher.group(1));
                    int sourceStack = Integer.parseInt(matcher.group(2)) - 1;
                    int destinationStack = Integer.parseInt(matcher.group(3)) - 1;

                    for (int i = 0; i < numberOfCrates; i++) {
                        stacks.get(destinationStack).push(stacks.get(sourceStack).pop());
                    }
                }
            }

            StringBuilder result = new StringBuilder();
            for (Stack<Character> stack : stacks) {
                result.append(stack.peek());
            }

            return result.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String partTwo() {
        return "";
    }
}
