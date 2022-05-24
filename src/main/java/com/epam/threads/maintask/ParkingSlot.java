package com.epam.threads.maintask;

import com.epam.threads.maintask.exceptions.ParkingException;

public class ParkingSlot {

    private final int id;
    private boolean available;

    public ParkingSlot(int id, boolean available) {
        this.id = id;
        this.available = available;
    }

    public void reserveSlot() throws ParkingException {
        if (available) {
            available = false;
            System.out.println("+ Parking Slot " + id + " has been chosen as the next available parking slot");
        } else {
            throw new ParkingException("Sorry, Parking Slot " + id + " can't accept cars");
        }
    }

    public void releaseSlot(Car car) throws ParkingException {
        if (!available) {
            available = true;
            System.out.println("- Parking Slot " + id + " has released Car " + car.getId());
        } else {
            throw new ParkingException("Sorry, Parking Slot " + id + " can't release any car");
        }
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "id=" + id +
                '}';
    }

}
