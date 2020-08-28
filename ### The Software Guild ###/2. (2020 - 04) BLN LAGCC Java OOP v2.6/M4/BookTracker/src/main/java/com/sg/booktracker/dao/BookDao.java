package com.sg.booktracker.dao;

import com.sg.booktracker.dto.Book;
import java.util.List;

/**
 *
 * @author Kyle David Rudy
 */
public interface BookDao {
    Book getBookByTitle(String title);
    List<Book> getAllBooks();
    Book addBook(Book book);
    void updateBook(Book book);
    void deleteBookByTitle(String title);
}
