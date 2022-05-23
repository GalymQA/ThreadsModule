package com.epam.threads.optionaltask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class RunnerOptional {

    private static final int NUMBER_OF_RUNWAYS = 5;
    private static final int NUMBER_OF_AIRPLANES = 10;
    private static final boolean RUNWAY_INITIAL_AVAILABILITY = true;
    private static final String AIRPORT_NAME = "Kennedy Airport";

    public static void main(String[] args) {
        List<Runway> runways = createRunways();
        Airport airport = createAirport(AIRPORT_NAME, runways);
        Semaphore semaphore = createSemaphore(airport);
        ReentrantLock lock = new ReentrantLock();
        List<AirplaneAtAirport> airplanesAtAirport = createAirplanesAtAirport(airport, semaphore, lock);
        startThreads(airplanesAtAirport);
    }

    private static List<Runway> createRunways() {
        List<Runway> runways = new ArrayList<>();
        for (int i = 0; i < RunnerOptional.NUMBER_OF_RUNWAYS; i++) {
            runways.add(new Runway(i, RUNWAY_INITIAL_AVAILABILITY));
        }
        return runways;
    }

    private static Airport createAirport(String name, List<Runway> runways) {
        Airport airport = new Airport(name, runways);
        System.out.println("Airport : " + airport);
        return airport;
    }

    private static List<AirplaneAtAirport> createAirplanesAtAirport(Airport airport,
                                                                    Semaphore semaphore,
                                                                    ReentrantLock lock) {
        List<AirplaneAtAirport> airplanesAtAirport = new ArrayList<>();
        for (int i = 0; i < RunnerOptional.NUMBER_OF_AIRPLANES; i++) {
            airplanesAtAirport.add(new AirplaneAtAirport(i, airport, semaphore, lock));
        }
        System.out.println("Airplanes : " + airplanesAtAirport);
        return airplanesAtAirport;
    }

    private static Semaphore createSemaphore(Airport airport) {
        return new Semaphore(airport.getCountOfRunways());
    }

    private static void startThreads(List<AirplaneAtAirport> airplanesAtAirport) {
        System.out.println("*** Starting all threads: ***");
        for (AirplaneAtAirport airplaneAtAirport: airplanesAtAirport) {
            new Thread(airplaneAtAirport).start();
        }
    }

}
