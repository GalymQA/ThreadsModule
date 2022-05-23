package com.epam.threads.optionaltask;

public class Airplane {

    private final int id;

    public Airplane(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "id=" + id +
                '}';
    }

}
