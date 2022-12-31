package aoc_2022.day07;

import aoc_2022.Day;

import java.util.Scanner;

public class Day07 extends Day<Integer, Integer> {

    private final FileSystem fs;

    public Day07() {
        super("src/aoc_2022/day07/day07_input.txt");
        fs = buildFileSystem();
    }

    @Override
    public Integer partOne() {
        return sumDirectorySizes(100000, fs.getRootDirectory());
    }

    @Override
    public Integer partTwo() {
        int minDirectorySize = (30000000 - (70000000 - fs.getRootDirectory().getSize()));
        return findDirectorySize(minDirectorySize, fs.getRootDirectory());
    }

    private FileSystem buildFileSystem() {
        Scanner scanner = getFileScanner();
        FileSystem fs = new FileSystem();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("$")) {
                String[] command = line.split(" ");
                if (command[1].equals("cd")) {
                    fs.changeDirectory(command[2]);
                }
            } else {
                String[] parts = line.split(" ");
                if (parts[0].equals("dir")) {
                    fs.addDirectoryEntity(parts[1]);
                } else {
                    fs.addFileEntity(parts[1], Integer.parseInt(parts[0]));
                }
            }
        }
        return fs;
    }

    /**
     * sum the sizes of directories with less than the given size
     */
    private int sumDirectorySizes(int size, DirectoryEntity dir) {
        int totalSize = 0;
        if (dir.getSize() <= size) {
            totalSize += dir.getSize();
        }
        for (DirectoryEntity c : dir.getChildDirectories()) {
            totalSize += sumDirectorySizes(size, c);
        }
        return totalSize;
    }

    /**
     * find the directory with minimum size that is also greater than the given size
     */
    private int findDirectorySize(int minSize, DirectoryEntity dir) {
        int result = dir.getSize();
        for (DirectoryEntity c : dir.getChildDirectories()) {
            if (c.getSize() >= minSize) {
                int size = findDirectorySize(minSize, c);
                if (size < result) {
                    result = size;
                }
            }
        }
        return result;
    }
}
