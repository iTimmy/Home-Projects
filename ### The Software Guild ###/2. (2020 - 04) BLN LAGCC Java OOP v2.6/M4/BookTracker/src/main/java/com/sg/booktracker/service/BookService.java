package com.sg.booktracker.service;

import com.sg.booktracker.dao.BookDao;
import com.sg.booktracker.dto.Book;
import java.util.List;

/**
 *
 * @author Kyle David Rudy
 */
public class BookService {
    
    private BookDao dao;

    public BookService(BookDao dao) {
        this.dao = dao;
    }
    
    public Book getBookByTitle(String title) {
        return dao.getBookByTitle(title);
    }
    
    public List<Book> getAllBooks() {
        return dao.getAllBooks();
    }
    
    public Book addBook(Book book) {
        return dao.addBook(book);
    }
    
    public void updateBook(Book book) {
        dao.updateBook(book);
    }
    
    public void deleteBookByTitle(String title) {
        dao.deleteBookByTitle(title);
    }
}
