package com.epam.threads.maintask;

import com.epam.threads.maintask.exceptions.ParkingException;

import java.util.List;

public class Parking {

    private static final int ONE = 1;

    private final String name;
    private final List<ParkingSlot> parkingSlots;

    public Parking(String name, List<ParkingSlot> parkingSlots) {
        if (parkingSlots.size() < ONE) {
            throw new IllegalArgumentException("Number of parking slots at a parking has to be positive.");
        }
        this.name = name;
        this.parkingSlots = parkingSlots;
    }

    public boolean isAnyParkingSlotAvailable() {
        boolean isAnyParkingSlotAvailable = false;
        for (ParkingSlot parkingSlot : parkingSlots) {
            isAnyParkingSlotAvailable = isAnyParkingSlotAvailable || parkingSlot.isAvailable();
        }
        return isAnyParkingSlotAvailable;
    }

    public ParkingSlot getAvailableParkingSlot() throws ParkingException {
        ParkingSlot availableParkingSlot = null;
        for (ParkingSlot parkingSlot : parkingSlots) {
            if (parkingSlot.isAvailable()) {
                availableParkingSlot = parkingSlot;
                availableParkingSlot.reserveSlot();
                return availableParkingSlot;
            }
        }
        return availableParkingSlot;
    }

    public int getNumberOfParkingSlots() {
        return parkingSlots.size();
    }

    @Override
    public String toString() {
        return "Parking{" +
                "name='" + name + '\'' +
                ", numberOfParkingSlots=" + getNumberOfParkingSlots() +
                '}';
    }

}
