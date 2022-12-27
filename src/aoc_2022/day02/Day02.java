package aoc_2022.day02;

import aoc_2022.Day;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day02 extends Day<Integer, Integer> {

    public Day02() {
        super("src/aoc_2022/Day02/day02_input.txt");
    }

    public Day02(String inputFilePath) {
        super(inputFilePath);
    }

    @Override
    public Integer partOne() {
        try {
            Scanner scanner = new Scanner(inputFile);

            int totalScore = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] moves = line.split(" ");
                totalScore += checkScore(Move.fromLabel(moves[0]), Move.fromLabel(moves[1]));
            }
            return totalScore;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer partTwo() {
        try {
            Scanner scanner = new Scanner(inputFile);

            int totalScore = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] moves = line.split(" ");
                totalScore += switch (moves[1]) {
                    case "X" -> Move.fromLabel(moves[0]).getWinsAgainst().pointValue;
                    case "Y" -> Move.fromLabel(moves[0]).pointValue + 3;
                    case "Z" -> Move.fromLabel(moves[0]).getLosesAgainst().pointValue + 6;
                    default -> throw new RuntimeException(String.format("Unexpected input: %s", moves[1]));
                };
            }
            return totalScore;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static int checkScore(Move playerOne, Move playerTwo) {
        if (playerTwo.getWinsAgainst().equals(playerOne)) {
            return playerTwo.pointValue + 6;
        } else if (playerOne.getWinsAgainst().equals(playerTwo)) {
            return playerTwo.pointValue;
        } else {
            return playerTwo.pointValue + 3;
        }
    }

    enum Move {
        ROCK("A", "X", 1, "SCISSORS", "PAPER"),
        PAPER("B", "Y", 2, "ROCK", "SCISSORS"),
        SCISSORS("C", "Z", 3, "PAPER", "ROCK");

        public final String playerOneLabel;

        public final String playerTwoLabel;

        public final int pointValue;

        private final String winsAgainst;

        private final String losesAgainst;


        Move(String playerOneLabel, String playerTwoLabel, int pointValue, String winsAgainst, String losesAgainst) {
            this.playerOneLabel = playerOneLabel;
            this.playerTwoLabel = playerTwoLabel;
            this.pointValue = pointValue;
            this.winsAgainst = winsAgainst;
            this.losesAgainst = losesAgainst;
        }

        public Move getWinsAgainst() {
            return valueOf(winsAgainst);
        }

        public Move getLosesAgainst() {
            return valueOf(losesAgainst);
        }

        static Move fromLabel(String label) {
            return switch (label) {
                case "A", "X" -> Move.ROCK;
                case "B", "Y" -> Move.PAPER;
                case "C", "Z" -> Move.SCISSORS;
                default -> throw new RuntimeException(String.format("Unexpected input: %s", label));
            };
        }
    }
}
