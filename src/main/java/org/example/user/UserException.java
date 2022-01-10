package org.example.user;

public class UserException extends RuntimeException{
    private String code;

    public UserException(String code, String message){
        super(message);
        this.code = code;
    }

    public UserException(String code, String message, Throwable cause){
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getLocalizedMessage() {
        return "{\"code\":" + code + ",\"message\":\"" + getMessage() + "\"}";
    }
}
