package org.example.backend.controller;

import org.example.backend.model.Book;
import org.example.backend.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable String id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/getAll")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PutMapping("/update/{id}")
    public Book updateBook(@RequestBody Book book) {
        Book updatedBook = bookService.getBookById(book.getId());
        if (updatedBook != null) {
            updatedBook.setTitle(book.getTitle());
            updatedBook.setAuthor(book.getAuthor());
            updatedBook.setGenre(book.getGenre());
            updatedBook.setPublicationYear(book.getPublicationYear());
            return bookService.updateBook(updatedBook);
        } else {
            return null; // Oder eine geeignete Fehlerbehandlung
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
    }
}
