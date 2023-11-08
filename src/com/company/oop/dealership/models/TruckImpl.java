package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Truck;
import com.company.oop.dealership.models.enums.VehicleType;

import static java.lang.String.format;

public class TruckImpl extends VehicleImpl implements Truck {

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
    public static final int WEIGHT_CAP_MIN = 1;
    public static final int WEIGHT_CAP_MAX = 100;
    private static final String WEIGHT_CAP_ERR = format(
            "Weight capacity must be between %d and %d!",
            WEIGHT_CAP_MIN,
            WEIGHT_CAP_MAX);

    private int weightCapacity;
    private int wheels = 8;

    public TruckImpl(String make, String model, double price, int weightCapacity) {
        super(make, model, price, VehicleType.TRUCK);
        setWeightCapacity(weightCapacity);


    }

    @Override
    public int getWeightCapacity() {
        return weightCapacity;
    }

    private void setWeightCapacity(int weightCapacity) {
        validateValueInRange(weightCapacity);

        this.weightCapacity = weightCapacity;
    }

    @Override
    public int getWheels() {
        return this.wheels = wheels;
    }

    public static void validateValueInRange(int value) {
        if (value < WEIGHT_CAP_MIN || value > WEIGHT_CAP_MAX) {
            throw new IllegalArgumentException(String.format(WEIGHT_CAP_ERR, WEIGHT_CAP_MIN, WEIGHT_CAP_MAX));
        }
    }

    @Override
    public String toString() {
        return String.format("Truck:%n%sWeight Capacity: %dt", super.toString(), getWeightCapacity());
    }
}