package org.example.user.processor;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.example.user.*;
import org.example.user.request.*;

public class UserProcessorFactory {
    private DynamoDBMapper mapper;

    public UserProcessorFactory() {
        final AmazonDynamoDB db = AmazonDynamoDBClientBuilder.defaultClient();
        this.mapper = new DynamoDBMapper(db);
    }

    public UserProcessorFactory(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public UserProcessor getProcessor(UserRequest userRequest){
        UserProcessor userProcessor;

        if (userRequest instanceof CreateUserRequest){
            userProcessor = new CreateUserProcessor(mapper);

        }else if (userRequest instanceof UpdateUserRequest){
            userProcessor = new UpdateUserProcessor(mapper);

        }else if (userRequest instanceof GetUserRequest){
            userProcessor = new GetUserProcessor(mapper);

        }else  if(userRequest instanceof DeleteUserRequest){
            userProcessor= new DeleteUserProcessor(mapper);
        }else {
            throw new UserException("1001", "Invalid user request");
        }
        return userProcessor;
    }

}