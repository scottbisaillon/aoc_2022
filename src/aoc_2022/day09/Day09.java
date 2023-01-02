package aoc_2022.day09;

import aoc_2022.Day;

import java.util.*;

public class Day09 extends Day<Integer, Integer> {
    public Day09() {
        super("src/aoc_2022/day09/day09_input.txt");
    }

    public Day09(String inputFilePath) {
        super(inputFilePath);
    }

    @Override
    public Integer partOne() {
        Set<Coordinates> visited = new HashSet<>();

        Coordinates currentHeadLocation = new Coordinates(0, 0);
        Coordinates currentTailLocation = new Coordinates(0, 0);

        visited.add(currentTailLocation);

        Scanner scanner = getFileScanner();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] move = line.split(" ");

            int amount = Integer.parseInt(move[1]);

            for (int i = 0; i < amount; i++) {
                currentHeadLocation = switch (move[0]) {
                    case "U" -> currentHeadLocation.moveUp();
                    case "R" -> currentHeadLocation.moveRight();
                    case "D" -> currentHeadLocation.moveDown();
                    case "L" -> currentHeadLocation.moveLeft();
                    default -> throw new RuntimeException(String.format("Invalid direction: '%s'", move[0]));
                };

                if (currentHeadLocation.isAdjacent(currentTailLocation)) {
                    continue;
                }

                currentTailLocation = currentTailLocation.moveToward(currentHeadLocation);
                visited.add(currentTailLocation);
            }
        }

        return visited.size();
    }

    @Override
    public Integer partTwo() {
        Set<Coordinates> visited = new HashSet<>();

        List<Coordinates> knots = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            knots.add(new Coordinates(0,0));
        }

        visited.add(new Coordinates(0, 0));

        Scanner scanner = getFileScanner();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] move = line.split(" ");

//            System.out.printf("---- Move: %s ----%n", line);

            int amount = Integer.parseInt(move[1]);

            for (int i = 0; i < amount; i++) {
//                Coordinates currentHeadLocation = knots.get(0);

                knots.set(0, switch (move[0]) {
                    case "U" -> knots.get(0).moveUp();
                    case "R" -> knots.get(0).moveRight();
                    case "D" -> knots.get(0).moveDown();
                    case "L" -> knots.get(0).moveLeft();
                    default -> throw new RuntimeException(String.format("Invalid direction: '%s'", move[0]));
                });

//                System.out.printf("Head moved %s -> %s%n", currentHeadLocation, knots.get(0));

                for (int j = 1; j < knots.size(); j++) {

                    if (knots.get(j).isAdjacent(knots.get(j - 1))) {
//                        System.out.printf("[%s] is adjacent to [%s] (%s, %s)%n", j, j -1, knots.get(j), knots.get(j - 1));
                        continue;
                    }

//                    Coordinates currentLocation = knots.get(j);
                    knots.set(j, knots.get(j).moveToward(knots.get(j - 1)));
//                    System.out.printf("[%s] moved %s -> %s%n", j, currentLocation, knots.get(j));

                    if (j == knots.size() - 1) {
//                        System.out.printf("Tail moved: %s%n", knots.get(j));
                        visited.add(knots.get(j));
//                        System.out.printf("Tail visited: %s%n", visited);
                    }
                }

            }
        }

        return visited.size();
    }

    record Coordinates(int x, int y) {

        boolean isAdjacent(Coordinates other) {
                return this.equals(other) ||
                        (Math.abs(this.x - other.x) == 1 && Math.abs(this.y - other.y) == 0) ||
                        (Math.abs(this.y - other.y) == 1 && Math.abs(this.x - other.x) == 0) ||
                        (Math.abs(this.x - other.x) == 1 && Math.abs(this.y - other.y) == 1);
            }

            Coordinates moveUp() {
                return new Coordinates(this.x, this.y + 1);
            }

            Coordinates moveRight() {
                return new Coordinates(this.x + 1, this.y);
            }

            Coordinates moveDown() {
                return new Coordinates(this.x, this.y - 1);
            }

            Coordinates moveLeft() {
                return new Coordinates(this.x - 1, this.y);
            }

            Coordinates moveToward(Coordinates other) {
                int xDiff = other.x - this.x;
                int yDiff = other.y - this.y;

                if (xDiff == 2 && yDiff > 0) { // move up and right
                    return new Coordinates(this.x + 1, this.y + 1);
                } else if (xDiff == 2 && yDiff < 0) { // move down and right
                    return new Coordinates(this.x + 1, this.y - 1);
                } else if (xDiff == -2 && yDiff > 0) { // move up and left
                    return new Coordinates(this.x - 1, this.y + 1);
                } else if (xDiff == -2 && yDiff < 0) { // move down and left
                    return new Coordinates(this.x - 1, this.y - 1);
                } else if (yDiff == 2 && xDiff > 0) { // move up and right
                    return new Coordinates(this.x + 1, this.y + 1);
                } else if (yDiff == 2 && xDiff < 0) { // move up and left
                    return new Coordinates(this.x - 1, this.y + 1);
                } else if (yDiff == -2 && xDiff > 0) { // move down and right
                    return new Coordinates(this.x + 1, this.y - 1);
                } else if (yDiff == -2 && xDiff < 0) { // move down and left
                    return new Coordinates(this.x - 1, this.y - 1);
                } else if (Math.abs(xDiff) == 2) {
                    return new Coordinates(this.x + (xDiff / Math.abs(xDiff)), this.y);
                } else if (Math.abs(yDiff) == 2) {
                    return new Coordinates(this.x, this.y + (yDiff / Math.abs(yDiff)));
                } else {
                    throw new RuntimeException(String.format("Invalid state: xDiff = %s, yDiff = %s", xDiff, yDiff));
                }
            }
    }
}
