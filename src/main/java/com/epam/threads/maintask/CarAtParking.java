package com.epam.threads.maintask;

import com.epam.threads.maintask.exceptions.ParkingException;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class CarAtParking extends Car implements Runnable {

    private static final int MIN_PARKING_TIME = 5;
    private static final int MAX_PARKING_TIME = 15;
    private static final int MIN_WAIT_TIME_OUT = 1;
    private static final int MAX_WAIT_TIME_OUT = 25;

    private final Parking parking;
    private final int parkTimeInSeconds;
    private final int waitTimeInSeconds;
    private final Semaphore semaphore;
    private final ReentrantLock lock;

    public CarAtParking(int id, Parking parking, Semaphore semaphore, ReentrantLock lock) {
        super(id);
        this.parking = parking;
        Random rand = new Random();
        this.parkTimeInSeconds = rand.nextInt((MAX_PARKING_TIME - MIN_PARKING_TIME) + 1) + MIN_PARKING_TIME;
        this.waitTimeInSeconds = rand.nextInt((MAX_WAIT_TIME_OUT - MIN_WAIT_TIME_OUT) + 1) + MIN_WAIT_TIME_OUT;
        this.semaphore = semaphore;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (semaphore.tryAcquire(waitTimeInSeconds, TimeUnit.SECONDS)) {
                ParkingSlot parkingSlot = getParkingSlotAndOccupyIt();
                moveOffFromParkingSlot(parkingSlot);
                semaphore.release();
            } else {
                leaveParkingDueToTimeOut();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ParkingSlot getParkingSlotAndOccupyIt() throws InterruptedException {
        ParkingSlot parkingSlot = null;
        try {
            lock.lock();
            parkingSlot = getParkingSlot();
            occupyParkingSlot(parkingSlot);
        } catch (ParkingException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        Thread.sleep(parkTimeInSeconds * 1000L);
        return parkingSlot;
    }

    private ParkingSlot getParkingSlot() throws ParkingException {
        if (!parking.isAnyParkingSlotAvailable()) {
            throw new ParkingException("No parking slot available. Should never happen.");
        }
        ParkingSlot parkingSlot = parking.getAvailableParkingSlot();
        if (parkingSlot == null) {
            throw new ParkingException("Parking slot can't be null. Should never happen.");
        }
        return parkingSlot;
    }

    private void occupyParkingSlot(ParkingSlot parkingSlot) {
        System.out.println("+ Car " + getId() + " is occupying Parking Slot " + parkingSlot.getId());
    }

    private void moveOffFromParkingSlot(ParkingSlot parkingSlot) {
        try {
            lock.lock();
            System.out.println("- Car " + getId() + " has moved off Parking Slot " + parkingSlot.getId());
            parkingSlot.releaseSlot(this);
        } catch (ParkingException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void leaveParkingDueToTimeOut() {
        System.out.println("Car " + this.getId() + " can't wait. Has to go!!!");
    }

    @Override
    public String toString() {
        return "CarAtParking{" +
                "id=" + super.getId() +
                ", parkTimeInSeconds=" + parkTimeInSeconds +
                ", waitTimeInSeconds=" + waitTimeInSeconds +
                '}';
    }

}
