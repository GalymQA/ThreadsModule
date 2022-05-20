package com.epam.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Runner {

    private static final int NUMBER_OF_CARS = 20;
    private static final String PARKING_NAME = "Walmart Parking";
    private static final int NUMBER_OF_SPACES_OF_PARKING = 6;

    public static void main(String[] args) {
        Parking parking = createParking();
        printToConsoleParkingInfo(parking);
        Semaphore semaphoreForParking = createSemaphoreForParking(parking);
        List<Car> carsToPark = createListOfCars(semaphoreForParking, parking);
        printToConsoleCars(carsToPark);
        startThreads(carsToPark);
    }

    private static Parking createParking() {
        return new Parking(PARKING_NAME, NUMBER_OF_SPACES_OF_PARKING);
    }

    private static void printToConsoleParkingInfo(Parking parking) {
        System.out.println("*** Created the parking: " + parking);
    }

    private static Semaphore createSemaphoreForParking(Parking parking) {
        return new Semaphore(parking.getNumberOfSpaces());
    }

    private static List<Car> createListOfCars(Semaphore semaphore, Parking parking) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_CARS; i++) {
            cars.add(new Car(i, semaphore, parking));
        }
        return cars;
    }

    private static void printToConsoleCars(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    private static void startThreads(List<Car> cars) {
        System.out.println("*** Starting all threads: ");
        for (Car car: cars) {
            new Thread(car).start();
        }
    }

}
