package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.contracts.Vehicle;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.FormattingHelpers;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract  class VehicleImpl implements Vehicle {
    public static final int MAKE_NAME_LEN_MIN = 2;
    public static final int MAKE_NAME_LEN_MAX = 15;
    public static final int MODEL_NAME_LEN_MIN = 1;
    public static final int MODEL_NAME_LEN_MAX = 15;
    public static final double PRICE_VAL_MIN = 0;
    public static final double PRICE_VAL_MAX = 1000000;

    private String make;
    private String model;
    private double price;
    private int wheels;

    private final VehicleType type;
    private List<Comment> comments = new ArrayList<>();


    public VehicleImpl(String make, String model, double price, VehicleType type) {
        setMake(make);
        setModel(model);
        setPrice(price);
        this.wheels = wheels;
        this.type = type;
        comments = new ArrayList<>(comments);
    }

    public String getMake() {
        return make;
    }

    private void setMake(String make) {
        ValidationHelpers.validateStringLength(make, MAKE_NAME_LEN_MIN, MAKE_NAME_LEN_MAX, "Make");
        this.make = make;
    }

    public String getModel() {
        return model;
    }


    private void setModel(String model) {
        ValidationHelpers.validateStringLength(model, MODEL_NAME_LEN_MIN, MODEL_NAME_LEN_MAX, "Model");
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }


    public void setPrice(double price) {
        ValidationHelpers.validateDecimalRange(price, PRICE_VAL_MIN, PRICE_VAL_MAX);
        FormattingHelpers.removeTrailingZerosFromDouble(price);
        this.price = price;
    }

    public int getWheels() {return wheels;}


    public VehicleType getType() {
        return type;
    }


    @Override
    public void addComment(Comment comment) {
        comments.add(comment);

    }

    @Override
    public void removeComment(Comment comment) {
        comments.remove(comment);

    }

    @Override
    public String toString() {
        return String.format("Make: %s%n" +
                "Model: %s%n" +
                "Wheels: %d%n" +
                "Price: $%s%n", getMake(), getModel(), getWheels(), FormattingHelpers.removeTrailingZerosFromDouble(getPrice()));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleImpl vehicle = (VehicleImpl) o;
        return Double.compare(vehicle.price, price) == 0 && wheels == vehicle.wheels && Objects.equals(make, vehicle.make) && Objects.equals(model, vehicle.model) && type == vehicle.type && Objects.equals(comments, vehicle.comments);
    }


}
