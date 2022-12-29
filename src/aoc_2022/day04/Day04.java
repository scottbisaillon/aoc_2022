package aoc_2022.day04;

import aoc_2022.Day;

import java.util.Arrays;
import java.util.List;

public class Day04 extends Day<Integer, Integer> {
    public Day04() {
        super("src/aoc_2022/day04/day04_input.txt");
    }

    @Override
    public Integer partOne() {
        return getLinesStream().reduce(0, (total, line) -> total + processSubset(line), Integer::sum);
    }

    @Override
    public Integer partTwo() {
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
