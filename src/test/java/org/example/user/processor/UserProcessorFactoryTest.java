package org.example.user.processor;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.example.user.User;
import org.example.user.request.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.mockito.Mockito;

import java.util.stream.Stream;

class UserProcessorFactoryTest {
    DynamoDBMapper mapper = Mockito.mock(DynamoDBMapper.class);

    @TestFactory
    public Stream<DynamicTest> type_request(){
        return Stream.of(CREATE_USER_REQUEST_TEST_CASE, GET_USER_REQUEST_TEST_CASE, UPDATE_USER_REQUEST_TEST_CASE, DELETE_USER_REQUEST_TEST_CASE).
                map(testCase->DynamicTest.dynamicTest(testCase.getCaseName(), ()->{
                    UserRequest userRequest = testCase.getUserRequest();
                    UserProcessorFactory userProcessorFactory = new UserProcessorFactory(mapper);

                    UserProcessor userProcessor = userProcessorFactory.getProcessor(userRequest);
                    Assertions.assertInstanceOf(testCase.getUserProcessorClase(), userProcessor);

                }));
    }

    private static class GetProcessorByRequestTypeTestCase{
        private final String caseName;
        private final UserRequest userRequest;
        private final Class<? extends UserProcessor> userProcessorClase;

        public GetProcessorByRequestTypeTestCase(String caseName, UserRequest userRequest, Class<? extends UserProcessor> userProcessorClase) {
            this.caseName = caseName;
            this.userRequest = userRequest;
            this.userProcessorClase = userProcessorClase;
        }

        public String getCaseName() {
            return caseName;
        }

        public UserRequest getUserRequest() {
            return userRequest;
        }

        public Class<? extends UserProcessor> getUserProcessorClase() {
            return userProcessorClase;
        }
    }
    private static final GetProcessorByRequestTypeTestCase CREATE_USER_REQUEST_TEST_CASE = new GetProcessorByRequestTypeTestCase(
            "CREATE_USER_REQUEST_TEST_CASE", new CreateUserRequest(new User()), CreateUserProcessor.class
    );

    private static final GetProcessorByRequestTypeTestCase GET_USER_REQUEST_TEST_CASE = new GetProcessorByRequestTypeTestCase(
             "GET_USER_REQUEST_TEST_CASE", new GetUserRequest(1), GetUserProcessor.class
    );

    private static final GetProcessorByRequestTypeTestCase UPDATE_USER_REQUEST_TEST_CASE = new GetProcessorByRequestTypeTestCase(
            "UPDATE_USER_REQUEST_TEST_CASE", new UpdateUserRequest(1, new User()), UpdateUserProcessor.class
    );
    private static final GetProcessorByRequestTypeTestCase DELETE_USER_REQUEST_TEST_CASE = new GetProcessorByRequestTypeTestCase(
            "DELETE_USER_REQUEST_TEST_CASE", new DeleteUserRequest(1), DeleteUserProcessor.class
    );

}