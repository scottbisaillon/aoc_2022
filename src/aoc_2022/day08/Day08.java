package aoc_2022.day08;

import aoc_2022.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day08 extends Day<Integer, Integer> {
    private final List<List<Integer>> grid;
    final int width;
    final int height;

    public Day08() {
        super("src/aoc_2022/day08/day08_input.txt");
        grid = buildTreeGrid();
        width = grid.get(0).size();
        height = grid.size();
    }

    @Override
    public Integer partOne() {
        int total = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (isVisible(i, j)) {
                    total++;
                }
            }
        }
        return total;
    }

    @Override
    public Integer partTwo() {
        int maxScore = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int score = calculateScenicScore(i, j);
                if (score > maxScore) {
                    maxScore = score;
                }
            }
        }
        return maxScore;
    }

    private List<List<Integer>> buildTreeGrid() {
        Scanner scanner = getFileScanner();
        List<List<Integer>> grid = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            grid.add(Arrays.stream(line.split("")).map(Integer::parseInt).toList());
        }
        return grid;
    }

    private boolean isExteriorTree(int i, int j) {
        return i == 0 || j == 0 || i == height - 1 || j == width - 1;
    }

    private boolean isVisible(int i, int j) {
        if (isExteriorTree(i, j)) {
            return true;
        }
        boolean visibleFromTop = true;
        boolean visibleFromRight = true;
        boolean visibleFromBottom = true;
        boolean visibleFromLeft = true;
        // top
        for (int row = 0; row < i; row++) {
            if (grid.get(row).get(j) >= grid.get(i).get(j)) {
                visibleFromTop = false;
                break;
            }
        }
        // right
        for (int col = j + 1; col < width; col++) {
            if (grid.get(i).get(col) >= grid.get(i).get(j)) {
                visibleFromRight = false;
                break;
            }
        }
        // bottom
        for (int row = i + 1; row < height; row++) {
            if (grid.get(row).get(j) >= grid.get(i).get(j)) {
                visibleFromBottom = false;
                break;
            }
        }
        // left
        for (int col = 0; col < j; col++) {
            if (grid.get(i).get(col) >= grid.get(i).get(j)) {
                visibleFromLeft = false;
                break;
            }
        }

        return visibleFromTop || visibleFromRight || visibleFromBottom || visibleFromLeft;
    }

    private int calculateScenicScore(int i, int j) {
        if (isExteriorTree(i, j)) {
            return 0;
        }

        int top = 0;
        int right = 0;
        int bottom = 0;
        int left = 0;

        // top
        for (int row = i - 1; row >= 0; row--) {
            if (grid.get(row).get(j).equals(grid.get(i).get(j))) {
                top++;
                break;
            } else if (grid.get(row).get(j) < grid.get(i).get(j)) {
                top++;
            } else {
                break;
            }
        }

        // right
        for (int col = j + 1; col < width; col++) {
            if (grid.get(i).get(col).equals(grid.get(i).get(j))) {
                right++;
                break;
            } else if (grid.get(i).get(col) < grid.get(i).get(j)) {
                right++;
            } else {
                break;
            }
        }

        // bottom
        for (int row = i + 1; row < height; row++) {
            if (grid.get(row).get(j).equals(grid.get(i).get(j))) {
                bottom++;
                break;
            } else if (grid.get(row).get(j) < grid.get(i).get(j)) {
                bottom++;
            } else {
                break;
            }
        }

        // left
        for (int col = j - 1; col >= 0; col--) {
            if (grid.get(i).get(col).equals(grid.get(i).get(j))) {
                left++;
                break;
            } else if (grid.get(i).get(col) < grid.get(i).get(j)) {
                left++;
            } else {
                break;
            }
        }

        return top * right * bottom * left;
    }
}
