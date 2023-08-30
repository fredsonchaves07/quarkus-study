package com.fredsonchaves07.acme.inventory.model;

public class Car {

    public Long id;

    public String licensePlateNumber;

    public String manufacturer;

    public String model;

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", licensePlateNumber='" + licensePlateNumber + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
