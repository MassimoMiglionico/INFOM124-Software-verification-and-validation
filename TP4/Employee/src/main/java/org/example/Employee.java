package org.example;

public abstract class Employee {
    protected float salary;

    protected Employee(float salary) {
        this.salary = salary;
    }

    public abstract float computeSalary();
}

class Worker extends Employee {
    public Worker(float salary) {
        super(salary);
    }
    @Override
    public float computeSalary() {
        return salary;
    }
}

class Supervisor extends Employee {

    protected float bonusPercentage;

    public Supervisor(float salary, float bonusPercentage) {
        super(salary);
        this.bonusPercentage = bonusPercentage;
    }
    @Override
    public float computeSalary() {
        return salary + (bonusPercentage * 0.5F);
    }

    public float computeYearBonus() {
        return salary + salary * 0.7F;
    }
}

class Main {
    public static void main(String[] args) {
        Worker worker1 = new Worker(25000);
        System.out.println(worker1.computeSalary());
        Worker worker2 = new Worker(35000);
        System.out.println(worker2.computeSalary());
        Supervisor supervisor = new Supervisor(35000, 2);
        System.out.println(supervisor.computeYearBonus());
    }
}