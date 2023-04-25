package org.example;

public class Department {
    private String nameDepartment;
    private Employee manager;

    public Department(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Employee getManager() {
        return manager;
    }
}

class Employee {
    private String name;
    private String lastName;
    private Department department;

    public Employee(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getManager() {
        return department.getManager();
    }
}

class ClientClass {
    public static void main(String[] args) {
        Department department = new Department("SNAIL");
        Employee employee = new Employee("Maquoi", "Jérôme");
        Employee manager = new Employee("Vanderose", "Benoit");
        department.setManager(manager);
        employee.setDepartment(department);

        System.out.println(employee.getManager());
    }
}