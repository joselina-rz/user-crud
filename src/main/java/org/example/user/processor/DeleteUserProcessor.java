package org.example.user.processor;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.example.user.request.DeleteUserRequest;
import org.example.user.User;
import org.example.user.request.UserRequest;

public class DeleteUserProcessor extends UserProcessor{

    public DeleteUserProcessor(DynamoDBMapper mapper) {
        super(mapper);
    }

    @Override
    public Object process(UserRequest request){
        User user = getMapper().load(User.class, ((DeleteUserRequest)request).getId());
        getMapper().delete(user);
        return user;
    }
}
