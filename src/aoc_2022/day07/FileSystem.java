package aoc_2022.day07;

public class FileSystem {
    private DirectoryEntity currentDirectory;
    private DirectoryEntity rootDirectory;

    public DirectoryEntity getCurrentDirectory() {
        return currentDirectory;
    }

    public DirectoryEntity getRootDirectory() {
        return rootDirectory;
    }

    public void changeDirectory(String name) {
        if (name.equals("/")) {
            DirectoryEntity root = new DirectoryEntity(null, name);
            this.currentDirectory = root;
            this.rootDirectory = root;
        } else if (name.equals("..")) {
            this.currentDirectory = (DirectoryEntity) this.currentDirectory.getParent();
        } else {
            if (this.currentDirectory.contains(name)) {
                this.currentDirectory = (DirectoryEntity) this.currentDirectory.getChild(name);
            } else {
                DirectoryEntity newChild = new DirectoryEntity(this.currentDirectory, name);
                this.currentDirectory.addChild(newChild);
                this.currentDirectory = newChild;
            }
        }
    }

    public void addFileEntity(String name, int size) {
        this.currentDirectory.addChild(new FileEntity(this.currentDirectory, name, size));
    }

    public void addDirectoryEntity(String name) {
        this.currentDirectory.addChild(new DirectoryEntity(this.currentDirectory, name));
    }
}
