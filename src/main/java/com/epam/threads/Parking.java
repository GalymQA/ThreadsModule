package com.epam.threads;

import java.util.ArrayList;
import java.util.List;

public class Parking {

    private static final int ONE = 1;

    private final String name;
    private final int numberOfSpaces;
    private final List<Car> parkedCars = new ArrayList<>();

    public Parking(String name, int numberOfSpaces) {
        if (numberOfSpaces < ONE) {
            throw new IllegalArgumentException("Number of spaces at a parking has to positive.");
        }
        this.name = name;
        this.numberOfSpaces = numberOfSpaces;
    }

    synchronized public void parkCar(Car car) throws InterruptedException {
        System.out.println("Car " + car.getId() + " is parking");
        parkedCars.add(car);
        System.out.println(" - Parked car : " + car);
        System.out.println(" - Number of parked cars now : " + parkedCars.size() + (parkedCars));
    }

    public void reside(Car car) throws InterruptedException {
        Thread.sleep(car.getParkTimeInSeconds() * 1000L);
    }

    synchronized public void leave(Car car) throws InterruptedException {
        System.out.println("Car " + car.getId() + " is leaving parking");
        parkedCars.remove(car);
        System.out.println(" - Left parking : " + car);
        System.out.println(" - Number of parked cars now : " + parkedCars.size() + (parkedCars));
    }

    synchronized public void leaveDueToTimeOut(Car car) {
        System.out.println("Car " + car.getId() + " can't wait. Has to go!!!");
    }

    public int getNumberOfSpaces() {
        return numberOfSpaces;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "name='" + name + '\'' +
                ", numberOfSpaces=" + numberOfSpaces +
                '}';
    }

}
