package ru.gb.exs3;

public class GenderException extends RuntimeException{
    public GenderException() {
        super("Incorrect person gender!");
    }
}
