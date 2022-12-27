package aoc_2022.day04;

import aoc_2022.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day04 extends Day {
    public Day04() {
        super("src/aoc_2022/day04/day04_input.txt");
    }

    public Day04(String inputFilePath) {
        super(inputFilePath);
    }

    @Override
    public int partOne() {
        return getLinesStream().reduce(0, (total, line) -> total + processSubset(line), Integer::sum);
    }

    @Override
    public int partTwo() {
        return getLinesStream().reduce(0, (total, line) -> total + processOverlap(line), Integer::sum);
    }

    int processSubset(String line) {
        String[] ranges = line.split(",");
        List<Integer> rangeOne = Arrays.stream(ranges[0].split("-")).map(Integer::parseInt).toList();
        List<Integer> rangeTwo = Arrays.stream(ranges[1].split("-")).map(Integer::parseInt).toList();

        if (rangeOne.get(0) >= rangeTwo.get(0) && rangeOne.get(1) <= rangeTwo.get(1)) {
            return 1;
        } else if (rangeTwo.get(0) >= rangeOne.get(0) && rangeTwo.get(1) <= rangeOne.get(1)) {
            return 1;
        } else {
            return 0;
        }
    }

    int processOverlap (String line) {
        String[] ranges = line.split(",");
        List<Integer> rangeOne = Arrays.stream(ranges[0].split("-")).map(Integer::parseInt).toList();
        List<Integer> rangeTwo = Arrays.stream(ranges[1].split("-")).map(Integer::parseInt).toList();

        if (rangeOne.get(0) >= rangeTwo.get(0) && rangeOne.get(0) <= rangeTwo.get(1)) {
            return 1;
        } else if (rangeTwo.get(0) >= rangeOne.get(0) && rangeTwo.get(0) <= rangeOne.get(1)) {
            return 1;
        } else {
            return 0;
        }
    }
}
