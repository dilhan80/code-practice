package com.library.internal;

import com.library.Book;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Book> books = new ArrayList<>();

    public void add(Book book) {
        books.add(book);
    }

    public int size() {
        return books.size();
    }
}
