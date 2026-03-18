package service.impl;

import model.User;
import service.UserManagementService;

import java.util.Arrays;

public class DefaultUserManagementService implements UserManagementService {

    private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user. Please, use another email";
    private static final String EMPTY_EMAIL_ERROR_MESSAGE = "You have to input email to register. Please, try one more time";
    private static final String NO_ERROR_MESSAGE = "";

    private static final int DEFAULT_USERS_CAPACITY = 10;

    private static DefaultUserManagementService instance;

    private User[] users;

    private DefaultUserManagementService() {
    }

    {
        users = new User[DEFAULT_USERS_CAPACITY];
    }

    @Override
    public String registerUser(User user) {

        if (user == null) return NO_ERROR_MESSAGE;

        if (user.getEmail().isEmpty()) {
            return EMPTY_EMAIL_ERROR_MESSAGE;
        } else if (getUserByEmail(user.getEmail()) != null) {
            return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
        } else {
            int userIndex = user.getId();

            if(users.length <= userIndex) {
                users = Arrays.copyOf(users, userIndex << 1);
            }

            users[userIndex] = user;
            return NO_ERROR_MESSAGE;
        }

    }

    public static UserManagementService getInstance() {
        if (instance == null) {
            instance = new DefaultUserManagementService();
        }
        return instance;
    }

    @Override
    public User[] getUsers() {
        return users;
    }

    @Override
    public User getUserByEmail(String userEmail) {
        for (User user : users) {
            if (user != null && userEmail.equalsIgnoreCase(user.getEmail())) {
                return user;
            }
        }
        return null;
    }

    void clearServiceState() {
        users = new User[DEFAULT_USERS_CAPACITY];
    }
}
