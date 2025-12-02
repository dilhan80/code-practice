package com.service;

import com.library.Book;

public class BookService {
    private int count = 0;

    public void addBook(Book book) {
        count++;
        System.out.println("Added: " + book.getTitle());
    }

    public int getCount() {
        return count;
    }
}
