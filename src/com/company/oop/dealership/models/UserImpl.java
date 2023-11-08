package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Comment;
import com.company.oop.dealership.models.contracts.User;
import com.company.oop.dealership.models.contracts.Vehicle;
import com.company.oop.dealership.models.enums.UserRole;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class UserImpl implements User {

    public static final int USERNAME_LEN_MIN = 2;
    public static final int USERNAME_LEN_MAX = 20;
    private static final String USERNAME_REGEX_PATTERN = "^[A-Za-z0-9]+$";
    private static final String USERNAME_PATTERN_ERR = "Username contains invalid symbols!";
//    private static final String USERNAME_LEN_ERR = format(
//            "Username must be between %d and %d characters long!",
//            USERNAME_LEN_MIN,
//            USERNAME_LEN_MAX);

    public static final int PASSWORD_LEN_MIN = 5;
    public static final int PASSWORD_LEN_MAX = 30;
    private static final String PASSWORD_REGEX_PATTERN = "^[A-Za-z0-9@*_-]+$";
    private static final String PASSWORD_PATTERN_ERR = "Password contains invalid symbols!";
//    private static final String PASSWORD_LEN_ERR = format(
//            "Password must be between %s and %s characters long!",
//            PASSWORD_LEN_MIN,
//            PASSWORD_LEN_MAX);

    public static final int LASTNAME_LEN_MIN = 2;
    public static final int LASTNAME_LEN_MAX = 20;
//    private static final String LASTNAME_LEN_ERR = format(
//            "Lastname must be between %s and %s characters long!",
//            LASTNAME_LEN_MIN,
//            LASTNAME_LEN_MAX);

    public static final int FIRSTNAME_LEN_MIN = 2;
    public static final int FIRSTNAME_LEN_MAX = 20;
//    private static final String FIRSTNAME_LEN_ERR = format(
//            "Firstname must be between %s and %s characters long!",
//            FIRSTNAME_LEN_MIN,
//            FIRSTNAME_LEN_MAX);

    private final static String NOT_AN_VIP_USER_VEHICLES_ADD = "You are not VIP and cannot add more than %d vehicles!";
    private final static String ADMIN_CANNOT_ADD_VEHICLES = "You are an admin and therefore cannot add vehicles!";

    private static final String YOU_ARE_NOT_THE_AUTHOR = "You are not the author of the comment you are trying to remove!";
    private final static String USER_TO_STRING = "Username: %s, FullName: %s %s, Role: %s";
    private final static String NO_VEHICLES_HEADER = "--NO VEHICLES--";
    private final static String USER_HEADER = "--USER %s--";
    private static final int NORMAL_ROLE_VEHICLE_LIMIT = 5;
    public static final String YOU_ARE_NOT_AN_ADMIN = "You are not an admin!";

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private UserRole userRole;
    private final List<Vehicle> vehicles;

    public UserImpl(String username, String firstName, String lastName, String password, UserRole userRole) {
        setUsername(username);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        this.userRole = userRole;
        vehicles = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserImpl user = (UserImpl) o;
        return username.equals(user.username) && firstName.equals(user.firstName)
                && lastName.equals(user.lastName) && userRole == user.userRole;
    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public UserRole getRole() {
        return userRole;
    }

    @Override
    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        if (userRole == UserRole.ADMIN) {
            throw new IllegalArgumentException(ADMIN_CANNOT_ADD_VEHICLES);
        } else if (userRole == UserRole.NORMAL && vehicles.size() >= NORMAL_ROLE_VEHICLE_LIMIT) {
            throw new IllegalArgumentException(String.format(NOT_AN_VIP_USER_VEHICLES_ADD, NORMAL_ROLE_VEHICLE_LIMIT));
        } else {
            vehicles.add(vehicle);

        }

    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);

    }

    @Override
    public void addComment(Comment commentToAdd, Vehicle vehicleToAddComment) {
        vehicleToAddComment.addComment(commentToAdd);
    }

    @Override
    public void removeComment(Comment commentToRemove, Vehicle vehicleToRemoveComment) {
        if (commentToRemove.getAuthor().equals(getUsername())) {
            vehicleToRemoveComment.removeComment(commentToRemove);
        } else {
            throw new IllegalArgumentException(YOU_ARE_NOT_THE_AUTHOR);
        }

    }


    @Override
    public boolean isAdmin() {
        if (!userRole.equals(UserRole.ADMIN)) {
            throw new IllegalArgumentException(YOU_ARE_NOT_AN_ADMIN);
        }
        return true;
    }


    private void setUsername(String username) {
        ValidationHelpers.validateStringLength(username, USERNAME_LEN_MIN, USERNAME_LEN_MAX, "Username");
        ValidationHelpers.validatePattern(username, USERNAME_REGEX_PATTERN, USERNAME_PATTERN_ERR);
        this.username = username;
    }

    private void setFirstName(String firstName) {
        ValidationHelpers.validateStringLength(firstName, FIRSTNAME_LEN_MIN, FIRSTNAME_LEN_MAX, "Firstname");
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        ValidationHelpers.validateStringLength(lastName, LASTNAME_LEN_MIN, LASTNAME_LEN_MAX, "Lastname");
        this.lastName = lastName;
    }

    private void setPassword(String password) {
        ValidationHelpers.validateStringLength(password, PASSWORD_LEN_MIN, PASSWORD_LEN_MAX, "Password");
        ValidationHelpers.validatePattern(password, PASSWORD_REGEX_PATTERN, PASSWORD_PATTERN_ERR);
        this.password = password;
    }

    private void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String printVehicles() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(USER_HEADER, username));
        sb.append(System.lineSeparator());
        if (vehicles.isEmpty()) {
            sb.append(String.format(NO_VEHICLES_HEADER));

        } else {
            int index = 1;
            for (Vehicle vehicle : vehicles) {
                sb.append(String.format("%d. %s%n", index, vehicle));

                if (vehicle.getComments().isEmpty()) {
                    sb.append(("--NO COMMENTS--"));
                    if (index != vehicles.size()) {
                        sb.append(System.lineSeparator());
                    }
                } else {
                    sb.append(String.format("--COMMENTS--%n%s--COMMENTS--%n", vehicle.getComments().toString().replaceAll("[\\[\\],]","")));


                }
                index++;
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format(USER_TO_STRING, username, firstName, lastName, userRole);
    }
}
