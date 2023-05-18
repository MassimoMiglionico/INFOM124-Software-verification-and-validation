package org.example;

public class Plane {
    private boolean wasInspected;
    private Seats seats;
    private Tank tank;
    private Logbook logbook;

    public Plane(boolean wasInspected, Seats seats, Tank tank, Logbook logbook) {
        this.wasInspected = wasInspected;
        this.seats = seats;
        this.tank = tank;
        this.logbook = logbook;
    }

    public Seats getSeats() {
        return seats;
    }

    public Tank getTank() {
        return tank;
    }

    public void setWasInspected(boolean wasInspected) {
        this.wasInspected = wasInspected;
    }

    public boolean isWasInspected() {
        return wasInspected;
    }

    public void fly(double distance) throws Exception {
        if (this.isWasInspected()){
            double fuelConsumed = distance * tank.getCONSUMED_FOR_A_KM();
            double currentFuelLevel = tank.getCurrentFuelLevel();
            if (fuelConsumed <= currentFuelLevel) {
                System.out.println("Flying " + distance + " km with " + seats.getNbOfOccupiedSeats() + " passengers");
                tank.setCurrentFuelLevel(currentFuelLevel - fuelConsumed);
            } else {
                System.out.println("Not enough fuel to fly " + distance + " km.");
            }
        } else {
            throw new Exception("The plane must be inspected before any departure.");
        }
    }
}

class Logbook {
    private String departureDateAndHour;
    private String arrivalDateAndHour;
    private String dateLastMaintenance;
    private String noteLastMaintenance;

    public Logbook(String departureDateAndHour, String arrivalDateAndHour, String dateLastMaintenance, String noteLastMaintenance) {
        if (departureDateAndHour.matches("\\d{4}-[01]\\d-[0-3]\\d\\s[0-2]\\d((:[0-5]\\d)?){2}") && arrivalDateAndHour.matches("\\d{4}-[01]\\d-[0-3]\\d\\s[0-2]\\d((:[0-5]\\d)?){2}") && dateLastMaintenance.matches("\\d{4}-[01]\\d-[0-3]\\d\\s[0-2]\\d((:[0-5]\\d)?){2}")) {
            this.departureDateAndHour = departureDateAndHour;
            this.arrivalDateAndHour = arrivalDateAndHour;
            this.dateLastMaintenance = dateLastMaintenance;
            this.noteLastMaintenance = noteLastMaintenance;
        }
    }
    public String departureDateAndHour(){
        return departureDateAndHour;
    }
    public String getArrivalDateAndHour(){
        return arrivalDateAndHour;
    }
    public String getDateLastMaintenance(){
        return dateLastMaintenance;
    }
    public String getNoteLastMaintenance(){
        return noteLastMaintenance;
    }
}

class Seats {
    private int totalNbFirstClassSeats;
    private int nbAvailableFirstClassSeats;
    private int totalNbSecondClassSeats;
    private int nbAvailableSecondClassSeats;
    private String FIRST_CLASS_SEAT = "first_class";


    public Seats(int totalNbFirstClassSeats, int nbAvailableFirstClassSeats, int totalNbSecondClassSeats, int nbAvailableSecondClassSeats) {
        this.totalNbFirstClassSeats = totalNbFirstClassSeats;
        this.nbAvailableFirstClassSeats = nbAvailableFirstClassSeats;
        this.totalNbSecondClassSeats = totalNbSecondClassSeats;
        this.nbAvailableSecondClassSeats = nbAvailableSecondClassSeats;

    }

    public int getNbOfOccupiedSeats() {
        return this.totalNbFirstClassSeats + this.totalNbSecondClassSeats - this.nbAvailableFirstClassSeats - nbAvailableSecondClassSeats;
    }

    public int numberAvailableSeats(String typeSeat) {
        if (typeSeat.equals(FIRST_CLASS_SEAT)) {
            return nbAvailableFirstClassSeats;
        } else {
            return nbAvailableSecondClassSeats;
        }
    }

    public void bookSeats(String typeSeat, int nbSeats) throws Exception {
        if (numberAvailableSeats(typeSeat) >= nbSeats) {
            if (typeSeat.equals(FIRST_CLASS_SEAT)) {
                nbAvailableFirstClassSeats = nbAvailableFirstClassSeats - nbSeats;
            } else {
                nbAvailableSecondClassSeats = nbAvailableSecondClassSeats - nbSeats;
            }
            System.out.println("Your seats are booked!");
        } else {
            throw new Exception("There is not enough available seats to make your booking");
        }
    }

    public double getOccupationRate() {
        return (double) (nbAvailableFirstClassSeats + nbAvailableSecondClassSeats) /(totalNbFirstClassSeats + totalNbSecondClassSeats);
    }
}

class Tank {
    private double fuelCapacity;
    private double currentFuelLevel = 0.0;
    private double CONSUMED_FOR_A_KM = 0.1;

    public Tank(double fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public double getCONSUMED_FOR_A_KM() {
        return CONSUMED_FOR_A_KM;
    }

    public void refuel(double amount) {
        double spaceLeftInTank = this.fuelCapacity - this.currentFuelLevel;
        if (amount <= spaceLeftInTank) {
            this.currentFuelLevel += amount;
            System.out.println("Refueled " + amount + " liters of fuel.");
        } else {
            System.out.println("Fuel tank can't hold that much fuel.");
        }
    }

    public void setCurrentFuelLevel(double currentFuelLevel) {
        this.currentFuelLevel = currentFuelLevel;
    }

    public double getCurrentFuelLevel() {
        return currentFuelLevel;
    }
}

class Main {
    public static void main(String[] args) throws Exception{
        Logbook logbook = new Logbook("2023-04-21 14:36:58", "2023-04-24 14:36:58","2023-04-24 14:36:58", "");
        Tank tank= new Tank(30000);
        Seats seats = new Seats(50, 50, 200, 200);
        Plane plane = new Plane(false, seats, tank, logbook);
        plane.getTank().setCurrentFuelLevel(10000);
        plane.getSeats().bookSeats("first_class", 7);
        plane.getSeats().bookSeats("second_class", 150);
        System.out.println(plane.getSeats().getOccupationRate());
        plane.setWasInspected(true);
        plane.fly(5000);
        double currentFuel = plane.getTank().getCurrentFuelLevel();
        System.out.println("There is " + currentFuel + " liters left.");
        plane.getTank().refuel(2000);
        plane.fly(10000);
        currentFuel = plane.getTank().getCurrentFuelLevel();
        System.out.println("There is " + currentFuel + " liters left.");
    }
}