package org.example.user.processor;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.example.user.request.UserRequest;

public abstract class UserProcessor {
    private final DynamoDBMapper mapper;


    public UserProcessor(DynamoDBMapper mapper) {

        this.mapper = mapper;
    }

    public DynamoDBMapper getMapper() {

        return mapper;
    }

    public abstract Object process(UserRequest userRequest);
}
