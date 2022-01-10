package org.example.user.processor;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.example.user.User;
import org.example.user.request.DeleteUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InOrder;
import org.mockito.Mockito;

class DeleteUserProcessorTest {
    DynamoDBMapper mapper = Mockito.mock(DynamoDBMapper.class);

    @Test
    public void delete_element_by_id(){
        User expectedUser = new User(1, "Juan", "Perez");
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest(1);
        UserProcessor userProcessorTest = new DeleteUserProcessor(mapper);

        Mockito.when(mapper.load(ArgumentMatchers.eq(User.class), Mockito.any(Integer.class))).thenReturn(expectedUser);
        User actualUser = (User)userProcessorTest.process(deleteUserRequest);
        Assertions.assertEquals(expectedUser, actualUser);

        InOrder inOrder = Mockito.inOrder(mapper);
        inOrder.verify(mapper, Mockito.times(1)).load(ArgumentMatchers.eq(User.class),ArgumentMatchers.eq(1));
        inOrder.verify(mapper, Mockito.times(1)).delete(expectedUser);

    }

}