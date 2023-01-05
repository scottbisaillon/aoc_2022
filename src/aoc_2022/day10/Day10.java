package aoc_2022.day10;

import aoc_2022.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day10 extends Day<Integer, String> {
    final CPU cpu;
    public Day10() {
        super("src/aoc_2022/day10/day10_input.txt");
        this.cpu = getCpu();
    }

    @Override
    public Integer partOne() {
        int signalSum = 0;
        for (int i = 20; i <= 220; i += 40) {
            signalSum += cpu.registerValues.get(i - 1) * i;
        }
        return signalSum;
    }

    @Override
    public String partTwo() {
        StringBuilder screen = new StringBuilder("\n");
        for (int i = 1; i <= 240; i++) {
            List<Integer> range = Arrays.asList(
                    cpu.registerValues.get(i - 1) - 1,
                    cpu.registerValues.get(i - 1),
                    cpu.registerValues.get(i - 1) + 1
            );
            screen.append(range.contains((i - 1) % 40) ? "#" : ".");
//            if (i % 5 == 0) {
//                screen.append("   ");
//            }
            if (i % 40 == 0) {
                screen.append("\n");
            }
        }
        return screen.toString();
    }

    private CPU getCpu() {
        CPU cpu = new CPU();
        Scanner scanner = getFileScanner();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("noop")) {
                cpu.executeNoop();
            } else {
                String[] instruction = line.split(" ");
                cpu.executeAdd(Integer.parseInt(instruction[1]));
            }
        }
        return cpu;
    }
    static class CPU {

        private int cycle = 0;
        private final List<Integer> registerValues = new ArrayList<>();

        public CPU() {
            registerValues.add(1);
        }

        public void executeNoop() {
            registerValues.add(registerValues.get(cycle++));
        }

        public void executeAdd(int amount) {
            registerValues.add(registerValues.get(cycle++));
            registerValues.add(registerValues.get(cycle++) + amount);
        }
    }
}
