package com.epam.threads.optionaltask;

import com.epam.threads.optionaltask.exceptions.AirportException;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class AirplaneAtAirport extends Airplane implements Runnable {

    private static final int TIME_TO_SLEEP = 3000;

    private final Semaphore semaphore;
    private final Airport airport;
    private final ReentrantLock lock;

    public AirplaneAtAirport(int id, Airport airport, Semaphore semaphore, ReentrantLock lock) {
        super(id);
        this.semaphore = semaphore;
        this.airport = airport;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            Runway runway = getRunwayAndMoveTo();
            takeOffFromRunway(runway);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Runway getRunwayAndMoveTo() throws InterruptedException {
        Runway runway = null;
        try {
            lock.lock();
            runway = getRunway();
            moveTo(runway);
        } catch (AirportException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        Thread.sleep(TIME_TO_SLEEP);
        return runway;
    }

    private Runway getRunway() throws AirportException {
        if (!airport.isAnyRunwayAvailable()) {
            throw new AirportException("No runway available. Should never happen.");
        }
        Runway runway = airport.getAvailableRunway();
        if (runway == null) {
            throw new AirportException("Runway can't be null. Should never happen.");
        }
        return runway;
    }

    private void moveTo(Runway runway) {
        System.out.println("+ Airplane " + getId() + " started to move to Runway " + runway.getId());
    }

    private void takeOffFromRunway(Runway runway) {
        try {
            lock.lock();
            System.out.println("- Airplane " + getId() + " has taken off Runway " + runway.getId());
            runway.releaseAirplane(this);
        } catch (AirportException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
