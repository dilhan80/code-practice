package com.test;

import com.library.Book;
import com.service.BookService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Book book = new Book("Java Modules in Action");
        BookService service = new BookService();
        service.addBook(book);
        System.out.println("Total books: " + service.getCount());
    }
}