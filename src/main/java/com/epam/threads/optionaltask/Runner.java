package com.epam.threads.optionaltask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private static final int NUMBER_OF_RUNWAYS = 5;
    private static final int NUMBER_OF_AIRPLANES = 10;

    public static void main(String[] args) {
        List<Runway> runways = createRunways();
        Airport airport = new Airport("Kennedy Airport", runways);
        System.out.println("Airport : " + airport);
        Semaphore semaphore = createSemaphore(airport);
        ReentrantLock lock = new ReentrantLock();
        List<AirplaneAtAirport> airplanesAtAirport = createAirplanesAtAirport(semaphore, airport, lock);
        System.out.println("Airplanes : " + airplanesAtAirport);
        startThreads(airplanesAtAirport);
    }

    private static List<Runway> createRunways() {
        List<Runway> runways = new ArrayList<>();
        for (int i = 0; i < Runner.NUMBER_OF_RUNWAYS; i++) {
            runways.add(new Runway(i, true));
        }
        return runways;
    }

    private static List<AirplaneAtAirport> createAirplanesAtAirport(Semaphore semaphore, Airport airport, ReentrantLock lock) {
        List<AirplaneAtAirport> airplanesAtAirport = new ArrayList<>();
        for (int i = 0; i < Runner.NUMBER_OF_AIRPLANES; i++) {
            airplanesAtAirport.add(new AirplaneAtAirport(i, semaphore, airport, lock));
        }
        return airplanesAtAirport;
    }

    private static Semaphore createSemaphore(Airport airport) {
        return new Semaphore(airport.getCountOfRunways());
    }

    private static void startThreads(List<AirplaneAtAirport> airplanesAtAirport) {
        System.out.println("*** Starting all threads: ");
        for (AirplaneAtAirport airplaneAtAirport: airplanesAtAirport) {
            new Thread(airplaneAtAirport).start();
        }
    }

}
