package com.epam.threads.optionaltask;

import java.util.ArrayList;
import java.util.List;

public class Airport {

    private final String name;
    private final List<Runway> runways;
    private final List<AirPlane> airplanesToTakeOff = new ArrayList<>();

    public Airport(String name, List<Runway> runways) {
        this.name = name;
        this.runways = runways;
    }

    public int getCountOfRunways() {
        return runways.size();
    }

    @Override
    public String toString() {
        return "Airport{" +
                "name='" + name + '\'' +
                ", runways=" + runways +
                '}';
    }

    private void airplaneStartMoveToRunway(AirPlane airPlane) {
        System.out.println("Airplane " + airPlane.getId() + "has started to move to a runway");
    }

    private void airplaneAcceptToRunway(AirPlane airPlane) {
        System.out.println("Runway has accepted Airplane " + airPlane.getId() + " to take off");
    }

    private void airplaneTakOffRunway(AirPlane airPlane) {
        System.out.println("Airplane " + airPlane.getId() + " has taken off from the runway");
    }

    private void airplaneReleaseRunway(AirPlane airPlane) {
        System.out.println("Airport " + airPlane.getId() + "start to move to a runway");
    }


}
