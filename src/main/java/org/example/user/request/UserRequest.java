package org.example.user.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "httpMethod")

@JsonSubTypes({

        @JsonSubTypes.Type(value = CreateUserRequest.class, name = "POST"),
        @JsonSubTypes.Type(value = DeleteUserRequest.class, name = "DELETE"),
        @JsonSubTypes.Type(value = GetUserRequest.class, name = "GET"),
        @JsonSubTypes.Type(value = UpdateUserRequest.class, name = "PUT"),
})
public abstract class UserRequest {

    public UserRequest() {

    }
}
