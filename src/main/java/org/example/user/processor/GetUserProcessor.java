package org.example.user.processor;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import org.example.user.request.GetUserRequest;
import org.example.user.User;
import org.example.user.UserException;
import org.example.user.request.UserRequest;

import java.util.List;
import java.util.stream.Collectors;

public class GetUserProcessor extends UserProcessor{


    public GetUserProcessor(DynamoDBMapper mapper) {
        super(mapper);
    }

    @Override
    public Object process(UserRequest request){
        if (((GetUserRequest)request).getId() == null){
            throw new UserException("1000", "User ID cannot be null");
        }
        if (((GetUserRequest)request).getId() == 0){
            List<User> users;
            users = getMapper().scan(User.class, new DynamoDBScanExpression()).stream().collect(Collectors.toList());
            return users;
        }else {
            User user;
            user = getMapper().load(User.class, ((GetUserRequest)request).getId());
            return user;
        }
    }
}
