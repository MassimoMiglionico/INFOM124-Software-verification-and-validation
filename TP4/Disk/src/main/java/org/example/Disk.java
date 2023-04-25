package org.example;

public class Disk {
    private final DiskConfiguration configuration;

    public Disk(DiskConfiguration configuration) {
        this.configuration = configuration;
    }
    public int getTotalSize() {
        return this.configuration.getBlocks() * this.configuration.getBlockSize();
    }
}

class DiskConfiguration {
    private int blocks;
    private int blockSize;

    public DiskConfiguration(int blocks, int blockSize) {
        this.blocks = blocks;
        this.blockSize = blockSize;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }
}

class Main {
    public static void main(String[] args) {
        DiskConfiguration diskConfiguration = new DiskConfiguration(4, 1024);
        Disk disk = new Disk(diskConfiguration);
        System.out.println(disk.getTotalSize());
    }
}