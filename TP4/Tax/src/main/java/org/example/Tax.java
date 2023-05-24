package org.example;

class Tax {
    double income;
    double taxRate;

    Tax(double income, double taxRate) {
        this.income = income;
        this.taxRate = taxRate;
    }

    public double getBaseTaxAmount() {
        return income * taxRate;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }
}

class Person {
    Tax tax;
    Person(Tax tax) {
        this.tax = tax;
    }

    public double getBaseTaxAmount() {
        return tax.getBaseTaxAmount();
    }
}

class Main {
    public static void main(String[] args) {
        Person person = new Person(new Tax(25000, 0.7));
        System.out.println(person.getBaseTaxAmount());
    }
}