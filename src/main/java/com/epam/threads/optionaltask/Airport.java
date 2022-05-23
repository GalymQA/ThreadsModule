package com.epam.threads.optionaltask;

import com.epam.threads.optionaltask.exceptions.AirportException;

import java.util.List;

public class Airport {

    private final String name;
    private final List<Runway> runways;

    public Airport(String name, List<Runway> runways) {
        this.name = name;
        this.runways = runways;
    }

    public int getCountOfRunways() {
        return runways.size();
    }

    public boolean isAnyRunwayAvailable() {
        boolean isAnyRunwayAvailable = false;
        for (Runway runway : runways) {
            isAnyRunwayAvailable = isAnyRunwayAvailable || runway.isAvailable();
        }
        return isAnyRunwayAvailable;
    }

    public Runway getAvailableRunway() throws AirportException {
        Runway availableRunway = null;
        for (Runway runway : runways) {
            if (runway.isAvailable()) {
                availableRunway = runway;
                availableRunway.reserve();
                return availableRunway;
            }
        }
        return availableRunway;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "name='" + name + '\'' +
                ", runways=" + runways +
                '}';
    }

}
