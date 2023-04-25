package org.example;

public abstract class Employee {
    protected float salary;
    protected float bonusPercentage;

    protected Employee(float salary, float bonusPercentage) {
        this.salary = salary;
        this.bonusPercentage = bonusPercentage;
    }

    public abstract float computeSalary();
    public float computeYearBonus() {
        return 0.0F;
    }
}

class Worker extends Employee {
    public Worker(float salary, float bonusPercentage) {
        super(salary, bonusPercentage);
    }
    @Override
    public float computeSalary() {
        return salary;
    }
}

class Supervisor extends Employee {
    public Supervisor(float salary, float bonusPercentage) {
        super(salary, bonusPercentage);
    }
    @Override
    public float computeSalary() {
        return salary + (bonusPercentage * 0.5F);
    }

    @Override
    public float computeYearBonus() {
        return salary + salary * 0.7F;
    }
}

class Main {
    public static void main(String[] args) {
        Worker worker1 = new Worker(25000, 0);
        System.out.println(worker1.computeSalary());
        Worker worker2 = new Worker(35000, 0);
        System.out.println(worker2.computeSalary());
        Supervisor supervisor = new Supervisor(35000, 2);
        System.out.println(supervisor.computeYearBonus());
    }
}