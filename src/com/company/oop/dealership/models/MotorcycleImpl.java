package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Motorcycle;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.ValidationHelpers;

public class MotorcycleImpl extends VehicleImpl implements Motorcycle {

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
    public static final int CATEGORY_LEN_MIN = 3;
    public static final int CATEGORY_LEN_MAX = 10;
//    private static final String CATEGORY_LEN_ERR = format(
//            "Category must be between %d and %d characters long!",
//            CATEGORY_LEN_MIN,
//            CATEGORY_LEN_MAX);

    private String category;
    private int wheels = 2;

    public MotorcycleImpl(String make, String model, double price, String category) {
        super(make, model, price, VehicleType.MOTORCYCLE);
        setCategory(category);
    }

    public String getCategory() {
        return category;
    }

    private void setCategory(String category) {
        ValidationHelpers.validateStringLength(category, CATEGORY_LEN_MIN, CATEGORY_LEN_MAX, "Category");
        this.category = category;
    }

    @Override
    public int getWheels() {
        return this.wheels = wheels;
    }

    @Override
    public String toString() {
        return String.format("Motorcycle:%n%sCategory: %s", super.toString(), getCategory());
    }
}