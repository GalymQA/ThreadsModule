package com.epam.threads.optionaltask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class RunnerOptionalTask {

    private static final int NUMBER_OF_RUNWAYS = 5;
    private static final int NUMBER_OF_AIRPLANES = 10;
    private static final boolean RUNWAY_INITIAL_AVAILABILITY = true;
    private static final String AIRPORT_NAME = "Kennedy Airport";

    public static void main(String[] args) {
        List<Runway> runways = createRunways();
        Airport airport = createAirport(runways);
        Semaphore semaphore = createSemaphore(airport.getCountOfRunways());
        ReentrantLock lock = createLock();
        List<AirplaneAtAirport> airplanesAtAirport = createAirplanesAtAirport(airport, semaphore, lock);
        printToConsoleAirplanes(airplanesAtAirport);
        startThreads(airplanesAtAirport);
    }

    private static List<Runway> createRunways() {
        List<Runway> runways = new ArrayList<>();
        for (int i = 0; i < RunnerOptionalTask.NUMBER_OF_RUNWAYS; i++) {
            runways.add(new Runway(i, RUNWAY_INITIAL_AVAILABILITY));
        }
        return runways;
    }

    private static Airport createAirport(List<Runway> runways) {
        Airport airport = new Airport(AIRPORT_NAME, runways);
        System.out.println("Airport : " + airport);
        return airport;
    }

    private static Semaphore createSemaphore(int permits) {
        return new Semaphore(permits);
    }

    private static ReentrantLock createLock() {
        return new ReentrantLock();
    }

    private static List<AirplaneAtAirport> createAirplanesAtAirport(Airport airport,
                                                                    Semaphore semaphore,
                                                                    ReentrantLock lock) {
        List<AirplaneAtAirport> airplanes = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_AIRPLANES; i++) {
            airplanes.add(new AirplaneAtAirport(i, airport, semaphore, lock));
        }
        return airplanes;
    }

    private static void printToConsoleAirplanes(List<AirplaneAtAirport> airplaneAtAirports) {
        System.out.println("Airplanes at airport : ");
        for (AirplaneAtAirport airplane : airplaneAtAirports) {
            System.out.println(airplane);
        }
    }

    private static void startThreads(List<AirplaneAtAirport> airplanesAtAirport) {
        System.out.println("*** Starting all threads: ***");
        for (AirplaneAtAirport airplane: airplanesAtAirport) {
            new Thread(airplane).start();
        }
    }

}
