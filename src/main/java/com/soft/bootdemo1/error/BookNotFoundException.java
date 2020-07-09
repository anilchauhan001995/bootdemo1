package com.soft.bootdemo1.error;


public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Integer id) {
        super("Book id Not Found: " + id);
    }
}
