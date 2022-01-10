package org.example.user.processor;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import org.example.user.User;
import org.example.user.UserException;
import org.example.user.request.GetUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GetUserProcessorTest {
    DynamoDBMapper mapper = Mockito.mock(DynamoDBMapper.class);


    @Test
    public void get_all_elements(){
        User user1 = new User(1, "Juan", "Perez" );
        User user2 = new User(2, "Jose", "Gomez" );
        User user3 = new User(3, "Ana", "Paz" );

        List<User> expectedUsersList = new ArrayList<User>();
        expectedUsersList.add(user1);
        expectedUsersList.add(user2);
        expectedUsersList.add(user3);

        PaginatedScanList paginatedScanListMock = Mockito.mock(PaginatedScanList.class);
        Mockito.when(paginatedScanListMock.stream()).thenReturn(expectedUsersList.stream());

        GetUserRequest getUserRequestTest = new GetUserRequest(0);
        UserProcessor userProcessorTest = new GetUserProcessor(mapper);

        Mockito.when(mapper.scan(ArgumentMatchers.eq(User.class), Mockito.any(DynamoDBScanExpression.class))).thenReturn(paginatedScanListMock);
        List<User> actualUserList = (List<User>) userProcessorTest.process(getUserRequestTest);
        assertThat(actualUserList).hasSameElementsAs(expectedUsersList);
    }
    @Test
    public void get_element_by_id(){
        User expectedUser = new User(1, "Juan", "Perez");
        GetUserRequest getUserRequestTest = new GetUserRequest(1);
        UserProcessor userProcessorTest = new GetUserProcessor(mapper);

        Mockito.when(mapper.load(ArgumentMatchers.eq(User.class), Mockito.any(Object.class))).thenReturn(expectedUser);
        User actualUser = (User) userProcessorTest.process(getUserRequestTest);
        Assertions.assertEquals(expectedUser, actualUser);
        Mockito.verify(mapper, Mockito.times(1)).load(ArgumentMatchers.eq(User.class), ArgumentMatchers.eq(1));
    }
    @Test
    public void get_user_id_null(){
        GetUserRequest getUserRequestTest = new GetUserRequest(null);
        UserProcessor userProcessorTest = new GetUserProcessor(mapper);

        UserException userException = Assertions.assertThrows(UserException.class,()-> userProcessorTest.process(getUserRequestTest));
        Assertions.assertEquals("1000", userException.getCode());
        Assertions.assertEquals("User ID cannot be null", userException.getMessage());
    }
    @Test
    public void element_not_found(){
        User expectedUser = null;
        GetUserRequest getUserRequestTest = new GetUserRequest(1);
        UserProcessor userProcessorTest = new GetUserProcessor(mapper);

        Mockito.when(mapper.load(ArgumentMatchers.eq(User.class), Mockito.any(Object.class))).thenReturn(expectedUser);
        User actualUser = (User) userProcessorTest.process(getUserRequestTest);
        Assertions.assertNull(actualUser);

    }

}