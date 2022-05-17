package com.epam.threads;

import java.util.concurrent.Semaphore;

public class Car implements Runnable {

    private static final int ZERO = 0;

    private int id;
    private int waitTimeInSeconds;
    private Semaphore semaphore;
    private Parking parking;

    public Car(int id, int waitTimeInSeconds, Semaphore semaphore, Parking parking) {
        if (id < ZERO | waitTimeInSeconds < ZERO) {
            throw new IllegalArgumentException("Only non-negative integers are allowed for a car.");
        }
        this.id = id;
        this.waitTimeInSeconds = waitTimeInSeconds;
        this.semaphore = semaphore;
        this.parking = parking;
    }


    public int getId() {
        return id;
    }

    public int getWaitTimeInSeconds() {
        return waitTimeInSeconds;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWaitTimeInSeconds(int waitTimeInSeconds) {
        this.waitTimeInSeconds = waitTimeInSeconds;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", waitTimeInSeconds=" + waitTimeInSeconds +
                '}';
    }

    @Override
    public void run() {
        try {
            System.out.println("Car " + id + " is waiting for a parking space.");
            semaphore.acquire();
            System.out.println("Car " + id + " gets a permit");
            parking.parkCar(this);
            Thread.sleep(this.getWaitTimeInSeconds()* 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Car " + id + " releases the permit");
        semaphore.release();
    }

}
