package aoc_2022.day07;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class DirectoryEntity extends FileSystemEntity {
    private final HashMap<String, FileSystemEntity> children = new HashMap<>();
    public DirectoryEntity(FileSystemEntity parent, String name) {
        super(parent, name);
    }

    public HashMap<String, FileSystemEntity> getChildren() {
        return children;
    }

    public FileSystemEntity getChild(String name) {
        return this.children.get(name);
    }

    public List<DirectoryEntity> getChildDirectories() {
        return this.children
                .values()
                .stream()
                .filter(fileSystemEntity -> fileSystemEntity instanceof DirectoryEntity)
                .map(DirectoryEntity.class::cast)
                .toList();
    }

    public void addChild(FileSystemEntity child) {
        this.children.put(child.getName(), child);
        this.updateSize(child.getSize());
        FileSystemEntity current = this.getParent();
        while (current != null) {
            current.updateSize(child.getSize());
            current = current.getParent();
        }
    }

    public boolean contains(String name) {
        return children.containsKey(name);
    }
}
