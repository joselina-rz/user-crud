package org.example.user.request;

public class DeleteUserRequest extends UserRequest {
    private Integer id;

    public DeleteUserRequest(Integer id) {
        this.id = id;
    }
    public DeleteUserRequest(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
