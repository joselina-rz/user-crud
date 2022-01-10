package org.example.user.request;

public class GetUserRequest extends UserRequest {
    private Integer id;

    public GetUserRequest(Integer id) {
        this.id = id;
    }

    public  GetUserRequest(){};

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
