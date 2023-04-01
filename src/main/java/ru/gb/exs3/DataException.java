package ru.gb.exs3;

public class DataException extends RuntimeException {
    public DataException() {
        super("Incorrect amount of data");
    }
}
