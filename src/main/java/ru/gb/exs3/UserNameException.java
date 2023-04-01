package ru.gb.exs3;

public class UserNameException extends RuntimeException{
    public UserNameException() {
        super("Invalid name entered!");
    }
}
