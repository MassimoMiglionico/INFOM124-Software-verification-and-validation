package org.example;


public class Drone {
    private int x;
    private int y;
    private Direction direction;


    public Drone(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void receive(String commandsSequence) {
        for (int i = 0; i < commandsSequence.length(); ++i) {
            char command = commandsSequence.charAt(i);

            if (command == 'l') {
                direction = direction.rotateLeft();
            } else if(command == 'r') {
                direction = direction.rotateRight();
            } else {
                move(command);
            }
        }
    }

    public void move(char command) {
        int displacement = -1;
        if (command == 'f') { displacement = 1; }
        // Displace Rover
        switch (direction) {
            case NORTH -> { y += displacement; }
            case SOUTH -> { y -= displacement; }
            case WEST -> { x -= displacement; }
            case EAST -> { x += displacement;}
        }
    }

    @Override
    public String toString() {
        return "Rover{" +
                "direction='" + direction + '\'' +
                ", y=" + y +
                ", x=" + x +
                '}';
    }
}

enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public Direction rotateLeft() {
        switch (this) {
            case NORTH -> { return WEST; }
            case EAST -> { return NORTH; }
            case SOUTH -> { return EAST; }
            case WEST -> { return SOUTH; }
            default -> { return null; }
        }
    }

    public Direction rotateRight() {
        switch (this) {
            case NORTH -> { return EAST; }
            case EAST -> { return SOUTH; }
            case SOUTH -> { return WEST; }
            case WEST -> { return NORTH; }
            default -> { return null; }
        }
    }
}

class Main {
    public static void main(String[] args) {
        Drone drone = new Drone(0, 0, Direction.NORTH);
        System.out.println(drone);
        drone.receive("rfffrflffrf");
        System.out.println(drone);
    }
}
