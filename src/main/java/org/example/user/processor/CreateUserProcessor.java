package org.example.user.processor;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.example.user.request.CreateUserRequest;
import org.example.user.User;
import org.example.user.request.UserRequest;

public class CreateUserProcessor extends UserProcessor {

    public CreateUserProcessor(DynamoDBMapper mapper) {
        super(mapper);
    }

    @Override
    public Object process(UserRequest request){
        User user = ((CreateUserRequest)request).getUser();
        getMapper().save(user);
        return user;
    }
}
