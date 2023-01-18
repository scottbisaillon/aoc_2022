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
                Location location;
                if (elevation == 'S') {
                    location = new Location(x, y, 'a');
                    start = location;
                } else if (elevation == 'E') {
                    location = new Location(x, y, 'z');
                    end = location;
                } else {
                    location = new Location(x, y, elevation);
                }
                row.add(location);
                it.next();
                x++;
            }
            grid.add(row);
            y++;
        }

        assert start != null;
        assert end != null;

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

        return null;
    }

    @Override
    public Integer partTwo() {
        return null;
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

    static class Location implements Comparable<Location> {
        final int x;
        final int y;

        final char elevation;
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
