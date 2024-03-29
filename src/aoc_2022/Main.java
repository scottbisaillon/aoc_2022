package aoc_2022;

import aoc_2022.day01.Day01;
import aoc_2022.day02.Day02;
import aoc_2022.day03.Day03;
import aoc_2022.day04.Day04;
import aoc_2022.day05.Day05;
import aoc_2022.day06.Day06;
import aoc_2022.day07.Day07;
import aoc_2022.day08.Day08;
import aoc_2022.day09.Day09;
import aoc_2022.day10.Day10;
import aoc_2022.day11.Day11;
import aoc_2022.day12.Day12;

import java.util.ArrayList;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        new ArrayList<>(Arrays.asList(
                new Day01(),
                new Day02(),
                new Day03(),
                new Day04(),
                new Day05(),
                new Day06(),
                new Day07(),
                new Day08(),
                new Day09(),
                new Day10(),
                new Day11(),
                new Day12()
        )).forEach(Day::solve);
    }
}
