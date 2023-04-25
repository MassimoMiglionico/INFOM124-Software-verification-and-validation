package org.example;

interface Engineer {
    String getType();
    void setType(String type);
    int getSalary();
    void setSalary(int salary);
    MileStone getMileStone();
    void setMileStone(MileStone mileStone);
}

interface MileStone {
    String work();
    String target();
}

public class ComputerEngineer implements Engineer {
    private String type;
    private int salary;
    private MileStone mileStone;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int getSalary() {
        return salary;
    }

    @Override
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public MileStone getMileStone() {
        return mileStone;
    }

    @Override
    public void setMileStone(MileStone mileStone) {
        this.mileStone = mileStone;
    }

    @Override
    public String toString() {
        return "ComputerEngineer [type=" + type + ", salary=" + salary + ", mileStone=" + mileStone + "]";
    }
}

class ComputerMileStone implements MileStone {

    @Override
    public String work() {
        return "Build a Billing MicroService";
    }

    @Override
    public String target() {
        return "Has to be finished in 14 PD";
    }

    @Override
    public String toString() {
        return "ComputerMileStone [work()=" + work() + ", target()=" + target();
    }
}

class CivilEngineer implements Engineer {
    private String type;
    private int salary;
    private MileStone mileStone;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int getSalary() {
        return salary;
    }

    @Override
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public MileStone getMileStone() {
        return mileStone;
    }

    @Override
    public void setMileStone(MileStone mileStone) {
        this.mileStone = mileStone;
    }

    @Override
    public String toString() {
        return "CivilEngineer [type=" + type + ", salary=" + salary + ", mileStone=" + mileStone + "]";
    }
}

class CivilMileStone implements MileStone {

    @Override
    public String work() {
        return "Create Twin Towers";
    }

    @Override
    public String target() {
        return "Has to be completed in 2 years";
    }

    @Override
    public String toString() {
        return "CivilMileStone [work()=" + work() + ", target()=" + target() + "]";
    }
}

class Manager {
    public static void main(String[] args) {
        Engineer comp = new ComputerEngineer();
        comp.setType("Computer Engineer");
        comp.setSalary(50000);
        comp.setMileStone(new ComputerMileStone());

        Engineer civil = new CivilEngineer();
        civil.setType("Civil Engineer");
        civil.setSalary(60000);

        civil.setMileStone(new CivilMileStone());

        System.out.println(comp);
        System.out.println("********************");
        System.out.println(civil);
    }
}
