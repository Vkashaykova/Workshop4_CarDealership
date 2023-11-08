package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Car;
import com.company.oop.dealership.models.enums.VehicleType;

import static java.lang.String.format;

public class CarImpl extends VehicleImpl implements Car {

    //    public static final int MAKE_NAME_LEN_MIN = 2;
//    public static final int MAKE_NAME_LEN_MAX = 15;
//    private static final String MAKE_NAME_LEN_ERR = format(
//            "Make must be between %s and %s characters long!",
//            MAKE_NAME_LEN_MIN,
//            MAKE_NAME_LEN_MAX);
//    public static final int MODEL_NAME_LEN_MIN = 1;
//    public static final int MODEL_NAME_LEN_MAX = 15;
//    private static final String MODEL_NAME_LEN_ERR = format(
//            "Model must be between %s and %s characters long!",
//            MODEL_NAME_LEN_MIN,
//            MODEL_NAME_LEN_MAX);
//    public static final double PRICE_VAL_MIN = 0;
//    public static final double PRICE_VAL_MAX = 1000000;
//    private static final String PRICE_VAL_ERR = format(
//            "Price must be between %.1f and %.1f!",
//            PRICE_VAL_MIN,
//            PRICE_VAL_MAX);
    public static final int CAR_SEATS_MIN = 1;
    public static final int CAR_SEATS_MAX = 10;
    private static final String CAR_SEATS_ERR = format(
            "Seats must be between %d and %d!",
            CAR_SEATS_MIN,
            CAR_SEATS_MAX);

    private int seats;
    private int wheels = 4;

    public CarImpl(String make, String model, double price, int seats) {
        super(make, model, price, VehicleType.CAR);
        setSeats(seats);


    }

    @Override
    public int getWheels() {
        return this.wheels = wheels;
    }

    @Override
    public int getSeats() {
        return seats;
    }

    private void setSeats(int seats) {
        validateValueInRange(seats);

        this.seats = seats;
    }


    public static void validateValueInRange(int value) {
        if (value < CAR_SEATS_MIN || value > CAR_SEATS_MAX) {
            throw new IllegalArgumentException(String.format(CAR_SEATS_ERR, CAR_SEATS_MIN, CAR_SEATS_MAX));
        }

    }

    @Override
    public String toString() {
        return String.format("Car:%n%sSeats: %d", super.toString(), getSeats());
    }
}