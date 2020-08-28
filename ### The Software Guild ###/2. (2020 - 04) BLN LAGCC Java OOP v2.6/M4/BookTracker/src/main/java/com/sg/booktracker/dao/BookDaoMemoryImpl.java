package com.sg.booktracker.dao;

import com.sg.booktracker.dto.Book;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kyle David Rudy
 */
public class BookDaoMemoryImpl implements BookDao {
    
    private List<Book> books = new ArrayList<>();

    @Override
    public Book getBookByTitle(String title) {
        for(Book book : books) {
            if(book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Override
    public Book addBook(Book book) {
        books.add(book);
        return book;
    }

    @Override
    public void updateBook(Book book) {
        int index = books.indexOf(book);
        if(index > -1) {
            books.remove(index);
            books.add(index, book);
        }
    }

    @Override
    public void deleteBookByTitle(String title) {
        Book book = getBookByTitle(title);
        int index = books.indexOf(book);
        if(index > -1) {
            books.remove(index);
        }
    }
    
}
