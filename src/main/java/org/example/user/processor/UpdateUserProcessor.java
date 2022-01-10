package org.example.user.processor;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.example.user.request.UpdateUserRequest;
import org.example.user.User;
import org.example.user.request.UserRequest;

public class UpdateUserProcessor extends UserProcessor{

    public UpdateUserProcessor(DynamoDBMapper mapper) {
        super(mapper);
    }


    @Override
    public Object process(UserRequest request){
        User user = ((UpdateUserRequest)request).getUser();
        getMapper().save(user);
        return user;
    }

}
