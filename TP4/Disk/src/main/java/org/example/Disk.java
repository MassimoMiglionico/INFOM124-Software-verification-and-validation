package org.example;

public class Disk {
    private int blocks;
    private int blockSize;

    public Disk(int blocks, int blockSize) {

        this.blocks = blocks;
        this.blockSize = blockSize;
    }

    public int getTotalSize() {
        return blocks * blockSize;
    }
}

class Main {
    public static void main(String[] args) {
        Disk disk = new Disk(4,1024);
        System.out.println(disk.getTotalSize());
    }
}