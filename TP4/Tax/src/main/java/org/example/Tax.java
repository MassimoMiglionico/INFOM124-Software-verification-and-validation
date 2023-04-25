package org.example;

class Tax {
    int status;
    double income;

    Tax(int status, double income) {
        this.status = status;
        this.income = income;
    }

    public double getBaseTaxAmount(int status) {
        return income * getTaxRate(status);
    }

    public double getTaxRate(int status) {
        if (status == 1) return 0.27;
        return 0.5;
    }
}

class Person extends Tax {
    Person(int status, double income) {
        super(status, income);
    }

    @Override
    public double getBaseTaxAmount(int status) {
        return this.income * 0.7;
    }
}

class Main {
    public static void main(String[] args) {
        Person person = new Person(1, 25000);
        System.out.println(person.getBaseTaxAmount(1));
    }
}