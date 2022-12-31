package aoc_2022.day07;

public class FileEntity extends FileSystemEntity {

    public FileEntity(FileSystemEntity parent, String name, int size) {
        super(parent, name);
        this.updateSize(size);
    }
}
