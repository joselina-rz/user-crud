package org.example.user.request;

import org.example.user.User;

public class UpdateUserRequest extends UserRequest {
    private Integer id;
    private User user;

    public UpdateUserRequest(Integer id, User user) {
        this.id = id;
        this.user = user;
    }
    public UpdateUserRequest(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
