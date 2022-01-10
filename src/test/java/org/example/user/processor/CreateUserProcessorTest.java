package org.example.user.processor;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.example.user.User;
import org.example.user.request.CreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

class CreateUserProcessorTest {
    DynamoDBMapper mapper = Mockito.mock(DynamoDBMapper.class);

    @Test
    public void insert_an_element(){
        User newUser = new User(1, "Juan", "Perez");
        CreateUserRequest userRequestTest = new CreateUserRequest(newUser);
        UserProcessor userProcessorTest = new CreateUserProcessor(mapper);

        User actualUser = (User)userProcessorTest.process(userRequestTest);
        Assertions.assertEquals(newUser, actualUser);
        Mockito.verify(mapper, Mockito.times(1)).save(ArgumentMatchers.eq(newUser));

    }

}