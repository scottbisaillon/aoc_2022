package aoc_2022;

import aoc_2022.day01.Day01;
import aoc_2022.day02.Day02;
import aoc_2022.day03.Day03;
import aoc_2022.day04.Day04;

import java.util.ArrayList;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        new ArrayList<>(Arrays.asList(
                new Day01(),
                new Day02(),
                new Day03(),
                new Day04()
        )).forEach(Day::solve);
    }
}