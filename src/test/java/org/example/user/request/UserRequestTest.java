package org.example.user.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

class UserRequestTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @TestFactory
    public Stream<DynamicTest> deserialize_by_request_type(){
        return Stream.of(DESERIALIZE_POST_METHOD, DESERIALIZE_GET_METHOD, DESERIALIZE_UPDATE_METHOD, DESERIALIZE_DELETE_METHOD).
                map(testCase->DynamicTest.dynamicTest(testCase.getCaseName(), ()->{

                    UserRequest userRequest = mapper.readValue(testCase.getJson(), UserRequest.class);

                    Assertions.assertInstanceOf((testCase.getUserRequest()).getClass(), userRequest);

                }));
    }
    private static class DeserializeByRequestTypeTestCase{
        private String caseName;
        private UserRequest userRequest;
        private String json;

        public DeserializeByRequestTypeTestCase(String caseName, UserRequest userRequest, String json) {
            this.caseName = caseName;
            this.userRequest = userRequest;
            this.json = json;
        }

        public String getCaseName() {
            return caseName;
        }

        public UserRequest getUserRequest() {
            return userRequest;
        }

        public String getJson() {
            return json;
        }
    }
    private static final DeserializeByRequestTypeTestCase DESERIALIZE_POST_METHOD = new DeserializeByRequestTypeTestCase(
            "DESERIALIZE_POST_METHOD", new CreateUserRequest(new User()),
            "{\"httpMethod\":\"POST\",\"user\":{ \"id\":1,\"firstName\":\"Juan\",\"lastName\":\"Perez\"}}"
    );

    private static final DeserializeByRequestTypeTestCase DESERIALIZE_GET_METHOD = new DeserializeByRequestTypeTestCase(
      "DESERIALIZE_GET_METHOD", new GetUserRequest(),
            "{\"httpMethod\":\"GET\",\"id\":1}"
    );

    private static final DeserializeByRequestTypeTestCase DESERIALIZE_UPDATE_METHOD = new DeserializeByRequestTypeTestCase(
      "DESERIALIZE_UPDATE_METHOD", new UpdateUserRequest(),
      "{\"httpMethod\":\"PUT\",\"user\":{ \"id\":1,\"firstName\":\"Jose\",\"lastName\":\"Perez\"}}"
    );

    private static final DeserializeByRequestTypeTestCase DESERIALIZE_DELETE_METHOD = new DeserializeByRequestTypeTestCase(
            "DESERIALIZE_DELETE_METHOD", new DeleteUserRequest(),
            "{\"httpMethod\":\"DELETE\",\"id\":1}"
    );

}