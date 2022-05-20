package com.epam.threads.optionaltask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Runner {

    private static final int NUMBER_OF_RUNWAYS = 5;
    private static final int NUMBER_OF_AIRPLANES = 10;

    public static void main(String[] args) {
        List<Runway> runways = createRunways();
        Airport airport = new Airport("Kennedy Airport", runways);
        System.out.println("Airport : " + airport);
        Semaphore semaphore = createSemaphore(airport);
        List<AirPlane> airplanes = createAirplanes(semaphore);
        System.out.println("Airplanes : " + airplanes);
        startThreads(airplanes);
    }

    private static List<Runway> createRunways() {
        List<Runway> runways = new ArrayList<>();
        for (int i = 0; i < Runner.NUMBER_OF_RUNWAYS; i++) {
            runways.add(new Runway(i));
        }
        return runways;
    }

    private static List<AirPlane> createAirplanes(Semaphore semaphore) {
        List<AirPlane> airplanes = new ArrayList<>();
        for (int i = 0; i < Runner.NUMBER_OF_AIRPLANES; i++) {
            airplanes.add(new AirPlane(i, semaphore));
        }
        return airplanes;
    }

    private static Semaphore createSemaphore(Airport airport) {
        return new Semaphore(airport.getCountOfRunways());
    }

    private static void startThreads(List<AirPlane> airPlanes) {
        System.out.println("*** Starting all threads: ");
        for (AirPlane airPlane: airPlanes) {
            new Thread(airPlane).start();
        }
    }

}
