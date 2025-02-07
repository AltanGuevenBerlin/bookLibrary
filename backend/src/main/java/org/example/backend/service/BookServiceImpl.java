package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.Book;
import org.example.backend.repo.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(Book book) {
        Book updatedBook = bookRepository.findById(book.getId()).orElse(null);
        if (updatedBook != null) {
            updatedBook.setTitle(book.getTitle());
            updatedBook.setAuthor(book.getAuthor());
            updatedBook.setGenre(book.getGenre());
            updatedBook.setPublicationYear(book.getPublicationYear());
            return bookRepository.save(updatedBook);
        } else {
            return null;
        }
    }
}
