package org.example.backend.service;

import org.example.backend.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    Book createBook (Book book);
    Book getBookById(String id);
    List<Book> getAllBooks();
    Book updateBook(Book book);
    void deleteBook(String id);
}
