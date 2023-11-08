package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.contracts.User;
import com.company.oop.dealership.utils.ValidationHelpers;

import java.util.List;

public class ShowUsersCommand extends BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;
    public static final String YOU_ARE_NOT_AN_ADMIN = "You are not an admin!";


    public ShowUsersCommand(VehicleDealershipRepository vehicleDealershipRepository) {
        super(vehicleDealershipRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        if (!getVehicleDealershipRepository().getLoggedInUser().isAdmin()) {
            throw new IllegalArgumentException(YOU_ARE_NOT_AN_ADMIN);
        }
        return showUsersCommand();

    }

    private String showUsersCommand() {
        List<User> users = getVehicleDealershipRepository().getUsers();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("--USERS--%n"));
        int index = 1;
        for (User user : users) {
            if (index != users.size()) {
                sb.append(String.format("%d. %s%n", index, user.toString()));
                index++;
            } else {
                sb.append(String.format("%d. %s", index, user.toString()));
            }
        }
        return sb.toString();

    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }


}