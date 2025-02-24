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
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();  // Gibt 404 Not Found zurück, wenn kein Buch gefunden wird
        }
        return ResponseEntity.ok(book);  // Gibt 200 OK zurück, wenn Buch gefunden wird
    }


    @GetMapping("/getAll")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody BookUpdateDTO updateDTO) {
        Book updatedBook = bookService.updateBook(id, updateDTO);
        return updatedBook != null ? ResponseEntity.ok(updatedBook) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }


}
