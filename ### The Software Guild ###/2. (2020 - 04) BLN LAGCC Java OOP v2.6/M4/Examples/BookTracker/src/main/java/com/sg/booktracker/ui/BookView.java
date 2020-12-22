package com.sg.booktracker.ui;

import com.sg.booktracker.dto.Book;
import java.util.List;

/**
 *
 * @author Kyle David Rudy
 */





public class BookView {
    
    private UserIO io;

    public BookView(UserIO io) {
        this.io = io;
    }
    
    public void displayBookTrackerBanner() {
        io.print("Welcome to the Book Tracker");
    }

    public int displayMenuAndGetChoice() {
        io.print("");
        io.print("Main Menu");
        io.print("1. View Books");
        io.print("2. View Book Details");
        io.print("3. Add Book");
        io.print("4. Update Book");
        io.print("5. Delete Book");
        io.print("6. Exit");
        
        return io.readInt("Please select an option:", 1, 6);
    }






    public void displayError(String error) {
        io.print("ERROR: " + error);
    }






    public void displayAllBooks(List<Book> books) {
        io.print("");
        io.print("All Books");
        books.stream();
                
    }






    public String getBookTitle() {
        io.print("");
        return io.readString("Enter book name:");
    }






    public void displayBookDetails(Book book) {        
        io.print("");
        io.print("Title: " + book.getTitle());
        io.print("Author: " + book.getAuthor());
        io.print("Year: " + book.getYear());
        io.print("Genre: " + book.getGenre());
    }






    public Book getNewBook() {
        io.print("");
        io.print("Enter new book info");
        String title = io.readString("Title:");
        String author = io.readString("Author:");
        int year = io.readInt("Year:");
        String genre = io.readString("Genre:");
        return new Book(title, author, year, genre);
    }






    public void displayAddSuccess() {
        io.print("Book added successfully");
    }






    public void displayExit() {
        io.print("Existing Book Tracker");
    }





    
    public String getBookTitleToDelete() {
        io.print("");
        return io.readString("Enter book name to delete:");
    }






    public void displayDeleteSuccess() {
        io.print("Book deleted successfully");
    }






    public String getBookTitleToUpdate() {
        io.print("");
        return io.readString("Enter book name to update:");    }






    public Book getUpdateBook(Book updateBook) {
        io.print("Updating " + updateBook.getTitle());
        String author = io.readString("Enter Author (" + updateBook.getAuthor() + "):");
        String year = io.readString("Enter Year (" + updateBook.getYear() + "):");
        String genre = io.readString("Enter Genre (" + updateBook.getGenre() + "):");
        if(!author.isBlank()) {
            updateBook.setAuthor(author);
        }
        if(!year.isBlank()) {
            updateBook.setYear(Integer.parseInt(year));
        }
        if(!genre.isBlank()) {
            updateBook.setGenre(genre);
        }
        return updateBook;
    }





    
    public void displayUpdateSuccess() {
        io.print("Book updated successfully");
    }

}
