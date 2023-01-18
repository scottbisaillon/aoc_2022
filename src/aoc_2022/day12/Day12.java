package aoc_2022.day12;

import aoc_2022.Day;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.*;

public class Day12 extends Day<Integer, Integer> {

    public Day12() {
        super("src/aoc_2022/day12/day12_input.txt");
    }

    @Override
    public Integer partOne() {
        Location start = null;
        Location end = null;

        List<List<Location>> grid = buildGrid();
        for (List<Location> row : grid) {
            for (Location location : row) {
                if (location.elevation == 'S') {
                    location.elevation = 'a';
                    start = location;
                } else if (location.elevation == 'E') {
                    location.elevation = 'z';
                    end = location;
                }
            }
        }

        assert start != null;
        assert end != null;

        return countShortestPath(grid, start, end);
    }

    @Override
    public Integer partTwo() {
        List<Location> starts = new ArrayList<>();
        Location end = null;

        List<List<Location>> grid = buildGrid();
        for (List<Location> row : grid) {
            for (Location location : row) {
                if (location.elevation == 'S' || location.elevation == 'a') {
                    location.elevation = 'a';
                    starts.add(location);
                } else if (location.elevation == 'E') {
                    location.elevation = 'z';
                    end = location;
                }
            }
        }

        int minSteps = Integer.MAX_VALUE;
        for (Location start : starts) {
            int steps = countShortestPath(grid, start, end);
            if (steps < minSteps) {
                minSteps = steps;
            }
            resetGrid(grid);
        }

        return minSteps;
    }

    int countShortestPath(List<List<Location>> grid, Location start, Location end) {
        Queue<Location> openSet = new PriorityQueue<>();
        openSet.add(start);
        start.gScore = 0;
        start.fScore = start.distanceFrom(end);

        while (!openSet.isEmpty()) {
            Location current = openSet.poll();
            if (current.equals(end)) {
                return end.gScore;
            }

            for (Location neighbor : getValidSteps(grid, current)) {
                int tentativeGScore = current.gScore + 1;
                if (tentativeGScore < neighbor.gScore) {
                    neighbor.gScore = tentativeGScore;
                    neighbor.fScore = tentativeGScore + neighbor.distanceFrom(end);
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    List<Location> getValidSteps(List<List<Location>> grid, Location location) {
        List<Location> validSteps = new ArrayList<>();
        if (location.y > 0) {
            Location up = grid.get(location.y - 1).get(location.x);
            if (location.isAccessible(up)) {
                validSteps.add(up);
            }
        }
        if (location.y < grid.size() - 1) {
            Location down = grid.get(location.y + 1).get(location.x);
            if (location.isAccessible(down)) {
                validSteps.add(down);
            }
        }
        if (location.x > 0) {
            Location left = grid.get(location.y).get(location.x - 1);
            if (location.isAccessible(left)) {
                validSteps.add(left);
            }
        }
        if (location.x < grid.get(0).size() - 1) {
            Location right = grid.get(location.y).get(location.x + 1);
            if (location.isAccessible(right)) {
                validSteps.add(right);
            }
        }
        return validSteps;
    }

    List<List<Location>> buildGrid() {
        List<List<Location>> grid = new ArrayList<>();
        Scanner scanner = getFileScanner();

        int y = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            CharacterIterator it = new StringCharacterIterator(line);
            int x = 0;
            List<Location> row = new ArrayList<>();
            while (it.current() != CharacterIterator.DONE) {
                char elevation = it.current();
                Location location = new Location(x, y, elevation);
                row.add(location);
                it.next();
                x++;
            }
            grid.add(row);
            y++;
        }
        return grid;
    }

    void resetGrid(List<List<Location>> grid) {
        for (List<Location> row : grid) {
            for (Location location : row) {
                location.reset();
            }
        }
    }

    static class Location implements Comparable<Location> {
        final int x;
        final int y;

        char elevation;
        Double fScore = Double.POSITIVE_INFINITY;
        Integer gScore = Integer.MAX_VALUE;

        public Location(int x, int y, char elevation) {
            this.x = x;
            this.y = y;
            this.elevation = elevation;
        }

        public double distanceFrom(Location other) {
            return Math.sqrt(Math.pow((this.x - other.x), 2) + Math.pow((this.y - other.y), 2));
        }

        public boolean isAccessible(Location other) {
            return other.elevation - this.elevation <= 1;
        }

        public void reset() {
            fScore = Double.POSITIVE_INFINITY;
            gScore = Integer.MAX_VALUE;
        }

        @Override
        public int compareTo(Location o) {
            return this.fScore.compareTo(o.fScore);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return x == location.x && y == location.y && elevation == location.elevation;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, elevation);
        }
    }
}
