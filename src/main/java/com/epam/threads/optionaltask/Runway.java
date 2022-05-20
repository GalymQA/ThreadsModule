package com.epam.threads.optionaltask;

public class Runway {

    private int id;
    private boolean availabilityStatus;

    public Runway(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Runway{" +
                "id=" + id +
                ", availabilityStatus=" + availabilityStatus +
                '}';
    }

}
