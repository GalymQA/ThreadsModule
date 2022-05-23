package com.epam.threads.optionaltask;

import com.epam.threads.optionaltask.exceptions.FlightException;

public class Runway {

    private final int id;
    private boolean available;

    public Runway(int id, boolean available) {
        this.id = id;
        this.available = available;
    }

    public void acceptAirplane() throws FlightException {
        if (available) {
            available = false;
            System.out.println(" + Runway " + id + " has been chosen as the next available runway");
        } else {
            throw new FlightException("Sorry, " + "Runway " + id + " can't accept airplane - " + available);
        }
    }

    public void releaseAirplane(Airplane airplane) throws FlightException {
        if (!available) {
            available = true;
            System.out.println(" - Runway " + id + " has released Airplane " + airplane.getId());
        } else {
            throw new FlightException("Sorry, " + "Runway " + id + " can't release airplane - " + available);
        }
    }

    public boolean isAvailable() {
        return available;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Runway{" +
                "id=" + id +
                ", available=" + available +
                '}';
    }

}
