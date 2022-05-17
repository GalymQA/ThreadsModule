package com.epam.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Runner {

    private static final int NUMBER_OF_CARS = 20;
    private static final int MIN_SECONDS_TO_WAIT = 5;
    private static final int MAX_SECONDS_TO_WAIT = 15;
    private static final String PARKING_NAME = "Walmart Parking";
    private static final int NUMBER_OF_SPACES_OF_PARKING = 6;

    public static void main(String[] args) {

        Parking parking = new Parking(PARKING_NAME, NUMBER_OF_SPACES_OF_PARKING);
        System.out.println(parking);

        Semaphore semaphore = new Semaphore(parking.getNumberOfSpaces(), true);
        List<Car> cars = createListOfCarsInRandomWay(semaphore, parking);
        printToConsoleCars(cars);
        startThreads(cars);
    }

    private static List<Car> createListOfCarsInRandomWay(Semaphore semaphore, Parking parking) {
        List<Car> cars = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < NUMBER_OF_CARS; i++) {
            int randomWaitTimeInSeconds = rand.nextInt((MAX_SECONDS_TO_WAIT - MIN_SECONDS_TO_WAIT) + 1) +
                    MIN_SECONDS_TO_WAIT;
            cars.add(new Car(i, randomWaitTimeInSeconds, semaphore, parking));
        }
        return cars;
    }

    private static void printToConsoleCars(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    private static void startThreads(List<Car> cars) {
        System.out.println("*** Starting all threads ***");
        for (Car car: cars) {
            new Thread(car).start();
        }
    }



}
