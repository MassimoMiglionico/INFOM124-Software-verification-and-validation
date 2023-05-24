package org.example;

abstract class Engineer {
    private String type;
    private int salary;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public abstract String work();
    public abstract String target();
}

public class ComputerEngineer extends Engineer {

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
        return "ComputerEngineer [type=" + getType() + ", salary=" + getSalary() + ", mileStone=" + "work()=" + work() + ", target()=" + target() + "]";
    }
}

class CivilEngineer extends Engineer {

    @Override
    public String work() { return "Create Twin Towers"; }

    @Override
    public String target() {
        return "Has to be completed in 2 years";
    }

    @Override
    public String toString() {
        return "CivilEngineer [type=" + getType() + ", salary=" + getSalary() + ", mileStone=" + "work()=" + work() + ", target()=" + target() + "]";
    }
}


class Manager {
    public static void main(String[] args) {
        Engineer comp = new ComputerEngineer();
        comp.setType("Computer Engineer");
        comp.setSalary(50000);

        Engineer civil = new CivilEngineer();
        civil.setType("Civil Engineer");
        civil.setSalary(60000);


        System.out.println(comp);
        System.out.println("********************");
        System.out.println(civil);
    }
}
