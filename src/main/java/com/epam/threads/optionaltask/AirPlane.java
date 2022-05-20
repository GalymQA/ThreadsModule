package com.epam.threads.optionaltask;

import java.util.concurrent.Semaphore;

public class AirPlane implements Runnable {

    private int id;
    private final Semaphore semaphore;

    public AirPlane(int id, Semaphore semaphore) {
        this.id = id;
        this.semaphore = semaphore;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "AirPlane{" +
                "id=" + id +
                '}';
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();

            semaphore.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        try {
//            if (semaphore.tryAcquire(this.getWaitTimeInSeconds(), TimeUnit.SECONDS)) {
//                parking.parkCar(this);
//                parking.reside(this);
//                parking.leave(this);
//                semaphore.release();
//            } else {
//                parking.leaveDueToTimeOut(this);
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}
