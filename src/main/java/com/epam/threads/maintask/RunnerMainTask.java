package com.epam.threads.maintask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class RunnerMainTask {

    private static final int NUMBER_OF_CARS = 20;
    private static final String PARKING_NAME = "Walmart Parking";
    private static final int NUMBER_OF_PARKING_SLOTS = 6;
    private static final boolean SLOT_INITIAL_AVAILABILITY = true;

    public static void main(String[] args) {
        List<ParkingSlot> parkingSlots = createParkingSlots();
        Parking parking = createParking(parkingSlots);
        Semaphore semaphore = createSemaphore(parking.getNumberOfParkingSlots());
        ReentrantLock lock = createLock();
        List<CarAtParking> carsToPark = createListOfCars(parking, semaphore, lock);
        printToConsoleCars(carsToPark);
        startThreads(carsToPark);
    }

    private static List<ParkingSlot> createParkingSlots() {
        List<ParkingSlot> parkingSlots = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_PARKING_SLOTS; i++) {
            parkingSlots.add(new ParkingSlot(i, SLOT_INITIAL_AVAILABILITY));
        }
        return parkingSlots;
    }

    private static Parking createParking(List<ParkingSlot> parkingSlots) {
        Parking parking = new Parking(PARKING_NAME, parkingSlots);
        System.out.println("*** Created the parking: " + parking);
        return parking;
    }

    private static Semaphore createSemaphore(int permits) {
        return new Semaphore(permits);
    }

    private static ReentrantLock createLock() {
        return new ReentrantLock();
    }

    private static List<CarAtParking> createListOfCars(Parking parking, Semaphore semaphore, ReentrantLock lock) {
        List<CarAtParking> cars = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_CARS; i++) {
            cars.add(new CarAtParking(i, parking, semaphore, lock));
        }
        return cars;
    }

    private static void printToConsoleCars(List<CarAtParking> cars) {
        for (CarAtParking car : cars) {
            System.out.println(car);
        }
    }

    private static void startThreads(List<CarAtParking> cars) {
        System.out.println("*** Starting all threads: ***");
        for (CarAtParking car : cars) {
            new Thread(car).start();
        }
    }

}
