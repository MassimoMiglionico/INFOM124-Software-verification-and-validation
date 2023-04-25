package org.example;

public class Drone {
    private int x;
    private int y;
    private String direction;

    public Drone(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void receive(String commandsSequence) {
        for (int i = 0; i < commandsSequence.length(); ++i) {
            String command = commandsSequence.substring(i, i + 1);

            if (command.equals("l") || command.equals("r")) {

                // Rotate Rover
                switch (direction) {
                    case "N" -> {
                        if (command.equals("r")) {
                            direction = "E";
                        } else {
                            direction = "W";
                        }
                    }
                    case "S" -> {
                        if (command.equals("r")) {
                            direction = "W";
                        } else {
                            direction = "E";
                        }
                    }
                    case "W" -> {
                        if (command.equals("r")) {
                            direction = "N";
                        } else {
                            direction = "S";
                        }
                    }
                    default -> {
                        if (command.equals("r")) {
                            direction = "S";
                        } else {
                            direction = "N";
                        }
                    }
                }
            } else {

                // Displace Rover
                int displacement1 = -1;

                if (command.equals("f")) {
                    displacement1 = 1;
                }
                int displacement = displacement1;

                switch (direction) {
                    case "N" -> y += displacement;
                    case "S" -> y -= displacement;
                    case "W" -> x -= displacement;
                    default -> x += displacement;
                }
            }
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

class Main {
    public static void main(String[] args) {
        Drone drone = new Drone(0, 0, "N");
        System.out.println(drone);
        drone.receive("rfffrflffrf");
        System.out.println(drone);
    }
}
