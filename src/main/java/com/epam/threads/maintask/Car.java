package com.epam.threads.maintask;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Car implements Runnable {

    private static final int MIN_PARK_TIME = 5;
    private static final int MAX_PARK_TIME = 15;
    private static final int MIN_TIME_OUT = 1;
    private static final int MAX_TIME_OUT = 25;

    private final int id;
    private final Semaphore semaphore;
    private final Parking parking;
    private final int parkTimeInSeconds;
    private final int waitTimeInSeconds;

    public Car(int id, Semaphore semaphore, Parking parking) {
        this.id = id;
        this.semaphore = semaphore;
        this.parking = parking;
        Random rand = new Random();
        this.parkTimeInSeconds = rand.nextInt((MAX_PARK_TIME - MIN_PARK_TIME) + 1) + MIN_PARK_TIME;
        this.waitTimeInSeconds = rand.nextInt((MAX_TIME_OUT - MIN_TIME_OUT) + 1) + MIN_TIME_OUT;
    }

    @Override
    public void run() {
        try {
            if (semaphore.tryAcquire(this.getWaitTimeInSeconds(), TimeUnit.SECONDS)) {
                parking.parkCar(this);
                parking.reside(this);
                parking.leave(this);
                semaphore.release();
            } else {
                parking.leaveDueToTimeOut(this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public int getParkTimeInSeconds() {
        return parkTimeInSeconds;
    }

    public int getWaitTimeInSeconds() {
        return waitTimeInSeconds;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", parkTimeInSeconds=" + parkTimeInSeconds +
                ", waitTimeInSeconds=" + waitTimeInSeconds +
                "}";
    }

}

