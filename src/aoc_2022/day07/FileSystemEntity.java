package aoc_2022.day07;

public abstract class FileSystemEntity {
    private final FileSystemEntity parent;
    private final String name;
    private int size = 0;

    public FileSystemEntity(FileSystemEntity parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public FileSystemEntity getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void updateSize(int size) {
        this.size += size;
    }
}
