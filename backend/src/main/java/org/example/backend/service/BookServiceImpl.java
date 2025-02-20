package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.BookUpdateDTO;
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

    public Book updateBook(String id, BookUpdateDTO updateDTO) {
        return bookRepository.findById(id).map(existingBook -> {
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
            return bookRepository.save(existingBook);
        }).orElse(null);
    }


}
