package cn.edu.springboot.exception;

public class UserNotExitException extends RuntimeException {

    public UserNotExitException(){
        super("user not exit");
    }
}
