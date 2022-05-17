package com.epam.threads;

import java.util.ArrayList;
import java.util.List;

public class Parking {

    private static final int ONE = 1;

    private String name;
    private int numberOfSpaces;
    private List<Car> parkedCars = new ArrayList<>();

    public Parking(String name, int numberOfSpaces) {
        if (numberOfSpaces < ONE) {
            throw new IllegalArgumentException("Only a positive integer is allowed for the number of spaces at a parking.");
        }
        this.name = name;
        this.numberOfSpaces = numberOfSpaces;
    }

    public void parkCar(Car car) {
        parkedCars.add(car);
        System.out.println(" - Parked car : " + car);
        System.out.println(" - Number of parked cars now : " + parkedCars.size());

    }

    public void printToConsoleParkedCars() {
        System.out.println("*************");
        for (Car car: parkedCars) {
            System.out.println("Parked car: " + car);
        }
        System.out.println("*************");
    }

    public String getName() {
        return name;
    }

    public int getNumberOfSpaces() {
        return numberOfSpaces;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfSpaces(int numberOfSpaces) {
        this.numberOfSpaces = numberOfSpaces;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "name='" + name + '\'' +
                ", numberOfSpaces=" + numberOfSpaces +
                '}';
    }

}
