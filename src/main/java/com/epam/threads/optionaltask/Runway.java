package com.epam.threads.optionaltask;

import com.epam.threads.optionaltask.exceptions.AirportException;

public class Runway {

    private final int id;
    private boolean available;

    public Runway(int id, boolean available) {
        this.id = id;
        this.available = available;
    }

    public void reserve() throws AirportException {
        if (available) {
            available = false;
            System.out.println("+ Runway " + id + " has been chosen as the next available runway");
        } else {
            throw new AirportException("Sorry, Runway " + id + " can't accept airplane");
        }
    }

    public void releaseAirplane(Airplane airplane) throws AirportException {
        if (!available) {
            available = true;
            System.out.println("- Runway " + id + " has released Airplane " + airplane.getId());
        } else {
            throw new AirportException("Sorry, Runway " + id + " can't release airplane");
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
        return "Runway{" +
                "id=" + id +
                ", available=" + available +
                '}';
    }

}
