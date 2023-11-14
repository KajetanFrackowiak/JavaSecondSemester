package com.example.demo;

public class CatFoundException extends RuntimeException {
    public CatFoundException() {
        super("Sorry, that cat is found and cannot be second.");
    }
}
