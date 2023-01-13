package aoc_2022.day11;

import aoc_2022.Day;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day11 extends Day<Integer, Long> {

    public Day11() {
        super("src/aoc_2022/day11/day11_input.txt");
    }

    private List<Monkey> buildInput() {
        List<Monkey> input = new ArrayList<>();
        Scanner scanner = getFileScanner();

        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();

            if (line.matches("Monkey.*")) {

                Monkey monkey = new Monkey();
                for (int i = 0; i < 5; i++) {
                    line = scanner.nextLine();
                    switch (i) {
                        case 0 -> {
                            String items = line.split(":")[1].trim();
                            monkey.addItems(Arrays.stream(items.split(", ")).map(Long::parseLong).toList());
                        }
                        case 1 -> {
                            String operationText = line.split("= ")[1];
                            String[] parts = operationText.split(" ");
                            boolean isAddOperation = parts[1].equals("+");
                            if (parts[2].equals("old")) {
                                monkey.setOperation(oldValue -> {
                                    if (isAddOperation) {
                                        return oldValue + oldValue;
                                    } else {
                                        return oldValue * oldValue;
                                    }
                                });
                            } else {
                                monkey.setOperation(oldValue -> {
                                    if (isAddOperation) {
                                        return oldValue + Integer.parseInt(parts[2]);
                                    } else {
                                        return oldValue * Integer.parseInt(parts[2]);
                                    }
                                });
                            }
                        }
                        case 2 -> {
                            String testText = line.split(": ")[1];
                            Pattern pattern = Pattern.compile("divisible by (\\d+)");
                            Matcher matcher = pattern.matcher(testText);
                            if (matcher.find()) {
                                monkey.setTest(Integer.parseInt(matcher.group(1)));
                            }
                        }
                        case 3 -> {
                            int trueMonkey = Integer.parseInt(line.substring(line.length() - 1));
                            monkey.setTrueMonkey(trueMonkey);
                        }
                        case 4 -> {
                            int falseMonkey = Integer.parseInt(line.substring(line.length() - 1));
                            monkey.setFalseMonkey(falseMonkey);
                        }
                    }
                }
                input.add(monkey);
            }

        }

        return input;
    }

    @Override
    public Integer partOne() {
        List<Monkey> monkeys = buildInput();

        for (int i = 0; i < 20; i++) {
            for (Monkey monkey : monkeys) {
                while (monkey.hasItems()) {
                    long item = monkey.inspect();
                    long newWorryLevel = monkey.getOperation().run(item) / 3;
                    int receivingMonkey = monkey.runTest(newWorryLevel);
                    monkeys.get(receivingMonkey).addItem(newWorryLevel);
                }
            }
        }

        int[] topTwo = new int[] {1, 1};
        for (Monkey monkey : monkeys) {
            int totalItemsInspected = (int) monkey.getTotalItemsInspected();
            for (int i = 0; i < topTwo.length; i++) {
                if (totalItemsInspected > topTwo[i]) {
                    int temp = topTwo[i];
                    topTwo[i] = totalItemsInspected;
                    totalItemsInspected = temp;

                }
            }
        }

        return topTwo[0] * topTwo[1];
    }

    @Override
    public Long partTwo() {
        List<Monkey> monkeys = buildInput();

        long commonMultiple = monkeys.stream().map(Monkey::getTestValue).reduce(1, (a, b) -> a * b);
        System.out.println(commonMultiple);

        for (int i = 0; i < 10000; i++) {
            for (Monkey monkey : monkeys) {
                while (monkey.hasItems()) {
                    long item = monkey.inspect() % commonMultiple;
                    long newWorryLevel = monkey.getOperation().run(item);
                    int receivingMonkey = monkey.runTest(newWorryLevel);
                    monkeys.get(receivingMonkey).addItem(newWorryLevel);
                }
            }
        }

        long[] topTwo = new long[] {1, 1};
        for (Monkey monkey : monkeys) {
            long totalItemsInspected = monkey.getTotalItemsInspected();
            for (int i = 0; i < topTwo.length; i++) {
                if (totalItemsInspected > topTwo[i]) {
                    long temp = topTwo[i];
                    topTwo[i] = totalItemsInspected;
                    totalItemsInspected = temp;

                }
            }
        }

        return topTwo[0] * topTwo[1];
    }

    interface Operation {
        long run(long oldValue);
    }

    static class Monkey {
        private long totalItemsInspected;
        private final Queue<Long> items = new ArrayDeque<>();
        private Operation operation;
        private int test;
        private int trueMonkey;
        private int falseMonkey;

        int runTest(long value) {
            return (value % test) == 0 ? trueMonkey : falseMonkey;
        }

        public void addItem(long item) {
            this.items.add(item);
        }

        public void addItems(List<Long> items) {
            this.items.addAll(items);
        }

        public long inspect() {
            totalItemsInspected++;
            return this.items.remove();
        }

        public boolean hasItems() {
            return !this.items.isEmpty();
        }

        public long getTotalItemsInspected() {
            return totalItemsInspected;
        }


        public Operation getOperation() {
            return operation;
        }

        public void setOperation(Operation operation) {
            this.operation = operation;
        }

        public int getTestValue() {
            return test;
        }

        public void setTest(int test) {
            this.test = test;
        }

        public void setTrueMonkey(int trueMonkey) {
            this.trueMonkey = trueMonkey;
        }

        public void setFalseMonkey(int falseMonkey) {
            this.falseMonkey = falseMonkey;
        }
    }
}
