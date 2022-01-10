package org.example.user.request;

import org.example.user.User;

public class CreateUserRequest extends UserRequest {
    private User user;

    public CreateUserRequest(User user) {
        this.user = user;
    }

    public CreateUserRequest() {

    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
