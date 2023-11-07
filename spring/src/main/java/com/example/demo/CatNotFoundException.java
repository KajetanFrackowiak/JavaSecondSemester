package com.example.demo;

public class CatNotFoundException extends RuntimeException{
    public CatNotFoundException() {super("Sorry, that cat is not found.");}
}
