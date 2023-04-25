package org.example;

public class Plane {
    private String FIRST_CLASS_SEAT = "first_class";
    private String SECOND_CLASS_SEAT = "second_class";
    private String dateOfConstruction;
    private int nbEngines;
    private String brandEngine;
    private String dateConstructionEngine;
    private boolean wasInspected;
    private double fuelCapacity;
    private double currentFuelLevel;
    private String altimeter;
    private int speed;
    private String radio;
    private boolean gps;
    private int priceFirstClass;
    private int totalNbFirstClassSeats;
    private int nbAvailableFirstClassSeats;
    private int priceSecondClass;
    private int totalNbSecondClassSeats;
    private int nbAvailableSecondClassSeats;
    private int nbCrew;
    private String pilotName;
    private String pilotCertification;
    private Logbook logbook;

    public Plane(String dateOfConstruction, int nbEngines, String brandEngine, String dateConstructionEngine, boolean wasInspected, double fuelCapacity, String altimeter, int speed, String radio, boolean gps, int priceFirstClass, int totalNbFirstClassSeats, int nbAvailableFirstClassSeats, int priceSecondClass, int totalNbSecondClassSeats, int nbAvailableSecondClassSeats, int nbCrew, String pilotName, String pilotCertification, Logbook logbook) {
        this.dateOfConstruction = dateOfConstruction;
        this.nbEngines = nbEngines;
        this.brandEngine = brandEngine;
        this.dateConstructionEngine = dateConstructionEngine;
        this.wasInspected = wasInspected;
        this.fuelCapacity = fuelCapacity;
        this.currentFuelLevel = 0.0;
        this.altimeter = altimeter;
        this.speed = speed;
        this.radio = radio;
        this.gps = gps;
        this.priceFirstClass = priceFirstClass;
        this.totalNbFirstClassSeats = totalNbFirstClassSeats;
        this.nbAvailableFirstClassSeats = nbAvailableFirstClassSeats;
        this.priceSecondClass = priceSecondClass;
        this.totalNbSecondClassSeats = totalNbSecondClassSeats;
        this.nbAvailableSecondClassSeats = nbAvailableSecondClassSeats;
        this.nbCrew = nbCrew;
        this.pilotName = pilotName;
        this.pilotCertification = pilotCertification;
        this.logbook = logbook;
    }

    public int getNbAvailableFirstClassSeats() {
        return nbAvailableFirstClassSeats;
    }

    public void setDateOfConstruction(String date) {
        if (date.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            this.dateOfConstruction = date;
        }
    }
    public String getDateOfConstruction() {
        return dateOfConstruction;
    }
    public void setPilotCertification(String certification) {
        if (certification.matches("/^[A-Z]-[A-Z]{4}|[A-Z]{2}-[A-Z]{3}|N[0-9]{1,5}[A-Z]{0,2}$/")) {
            this.pilotCertification = certification;
        }
    }

    public void setWasInspected(boolean wasInspected) {
        this.wasInspected = wasInspected;
    }

    public boolean isWasInspected() {
        return wasInspected;
    }

    public void fly(double distance) throws Exception {
        if (this.isWasInspected()){
            double fuelConsumed = distance * 0.1;
            if (fuelConsumed <= this.currentFuelLevel) {
                System.out.println("Flying " + distance + " km with " + this.getNbOfOccupiedSeats() + " passengers");
                this.currentFuelLevel -= fuelConsumed;
            } else {
                System.out.println("Not enough fuel to fly " + distance + " km.");
            }
        } else {
            throw new Exception("The plane must be inspected before any departure.");
        }
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

    public int computeSeatPrice(String typeSeat, int nbNeededSeats) throws Exception {
        if (numberAvailableSeats(typeSeat) >= nbNeededSeats) {
            if (typeSeat.equals(FIRST_CLASS_SEAT)) {
                return nbNeededSeats * priceFirstClass;
            } else {
                return nbNeededSeats * priceSecondClass;
            }
        } else {
            throw new Exception("There is not enough available seats");
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
}

class Main {
    public static void main(String[] args) throws Exception{
        Logbook logbook = new Logbook("2023-04-21 14:36:58", "2023-04-24 14:36:58","2023-04-24 14:36:58", "");
        Plane plane = new Plane("04-02-99", 4, "Porsche", "04-03-2014", false, 30000, "A042", 945, "don't know radio name :(", true, 500, 50, 50, 250, 200, 200, 15, "Jean-Kévin", "N390HA", logbook);
        plane.setCurrentFuelLevel(10000);
        plane.bookSeats("first_class", 7);
        plane.bookSeats("second_class", 150);
        System.out.println(plane.getOccupationRate());
        plane.setWasInspected(true);
        plane.fly(5000);
        double currentFuel = plane.getCurrentFuelLevel();
        System.out.println("There is " + currentFuel + " liters left.");
        plane.refuel(2000);
        plane.fly(10000);
        currentFuel = plane.getCurrentFuelLevel();
        System.out.println("There is " + currentFuel + " liters left.");
    }
}