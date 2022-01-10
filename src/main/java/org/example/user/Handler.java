package org.example.user;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.example.user.processor.UserProcessor;
import org.example.user.processor.UserProcessorFactory;
import org.example.user.request.UserRequest;

public class Handler  implements RequestHandler<UserRequest, Object>{

    private final UserProcessorFactory userProcessorFactory;

    public Handler(UserProcessorFactory userProcessorFactory) {

        this.userProcessorFactory = userProcessorFactory;
    }

    public Handler() {

        this.userProcessorFactory = new UserProcessorFactory();
    }


    @Override
    public Object handleRequest(UserRequest userRequest, Context context) {

        UserProcessor userProcessor = userProcessorFactory.getProcessor(userRequest);
        return userProcessor.process(userRequest);

    }


}
