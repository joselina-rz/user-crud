package org.example.user;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import org.example.user.processor.UserProcessor;
import org.example.user.processor.UserProcessorFactory;
import org.example.user.request.UserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class HandlerTest {

    DynamoDBMapper mapper = Mockito.mock(DynamoDBMapper.class);

    @Test
    public void happy_path(){
        UserProcessorFactory userProcessorFactory = Mockito.mock(UserProcessorFactory.class);
        Handler handler = new Handler(userProcessorFactory);

        UserRequest userRequest = Mockito.mock(UserRequest.class);
        UserProcessor userProcessor = Mockito.mock(UserProcessor.class);

        Mockito.when(userProcessorFactory.getProcessor(Mockito.any())).thenReturn(userProcessor);
        Mockito.when(userProcessor.process(Mockito.any())).thenReturn("un usuario");
        String  rtaUserProcessor = (String) handler.handleRequest(userRequest, Mockito.mock(Context.class));

        Mockito.verify(userProcessorFactory, Mockito.times(1)).getProcessor(userRequest);
        Mockito.verify(userProcessor, Mockito.times(1)).process(userRequest);
        Assertions.assertEquals("un usuario", rtaUserProcessor);



    }
}