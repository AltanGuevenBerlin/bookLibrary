package org.example.backend.controller;

import org.example.backend.dto.BookUpdateDTO;
import org.example.backend.model.Book;
import org.example.backend.service.BookService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody BookUpdateDTO updateDTO) {
        Book existingBook = bookService.getBookById(id);
        if (existingBook != null) {
            if (updateDTO.getTitle() != null) {
                existingBook.setTitle(updateDTO.getTitle());
            }
            if (updateDTO.getAuthor() != null) {
                existingBook.setAuthor(updateDTO.getAuthor());
            }
            if (updateDTO.getGenre() != null) {
                existingBook.setGenre(updateDTO.getGenre());
            }
            if (updateDTO.getPublicationYear() != null) {
                existingBook.setPublicationYear(updateDTO.getPublicationYear());
            }
            Book updatedBook = bookService.updateBook(existingBook);
            return ResponseEntity.ok(updatedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
    }
}
