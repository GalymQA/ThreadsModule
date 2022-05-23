package com.epam.threads.optionaltask;

import com.epam.threads.optionaltask.exceptions.FlightException;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class AirplaneAtAirport extends Airplane implements Runnable {

    private static final int TIME_TO_SLEEP = 3000;

    private final Semaphore semaphore;
    private final Airport airport;
    private final ReentrantLock lock;

    public AirplaneAtAirport(int id, Semaphore semaphore, Airport airport, ReentrantLock lock) {
        super(id);
        this.semaphore = semaphore;
        this.airport = airport;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            Runway runway = this.getNextAvailableRunway();
            this.moveToAvailableRunway(runway);
            this.takeOffFromRunway(runway);
            semaphore.release();
        } catch (InterruptedException | FlightException e) {
            e.printStackTrace();
        }
    }

    synchronized private Runway getNextAvailableRunway() throws FlightException {
        try {
            lock.lock();
            if (!airport.isAnyRunwayAvailable()) {
                throw new FlightException("No runway available. Semaphore is broken.");
            }
            Runway runway = airport.getNextAvailableRunway();
            if (runway == null) {
                throw new FlightException("Runway can't be null.");
            }
            return runway;
        } finally {
            lock.unlock();
        }
    }

    synchronized private void moveToAvailableRunway(Runway runway) throws InterruptedException {
        System.out.println(" + Airplane " + getId() + " started to move to Runway " + runway.getId());
        Thread.sleep(TIME_TO_SLEEP);
    }

    private void takeOffFromRunway(Runway runway) {
        try {
            lock.lock();
            System.out.println(" - Airplane " + getId() + " has taken off the Runway " + runway.getId());
            runway.releaseAirplane(this);
        } catch (FlightException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
