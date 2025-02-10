package org.example.backend.service;

import org.example.backend.model.Book;
import org.example.backend.repo.BookRepository;
import org.example.backend.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Mocks initialisieren
        book = new Book("1", "Test Title", "Test Author", "Test Genre", "2023");
    }

    @Test
    void testCreateBook() {
        // Arrange
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // Act
        Book createdBook = bookService.createBook(book);

        // Assert
        assertNotNull(createdBook);
        assertEquals("Test Title", createdBook.getTitle());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testGetBookById() {
        // Arrange
        when(bookRepository.findById("1")).thenReturn(Optional.of(book));

        // Act
        Book foundBook = bookService.getBookById("1");

        // Assert
        assertNotNull(foundBook);
        assertEquals("Test Title", foundBook.getTitle());
        verify(bookRepository, times(1)).findById("1");
    }

    @Test
    void testGetBookByIdNotFound() {
        // Arrange
        when(bookRepository.findById("1")).thenReturn(Optional.empty());

        // Act
        Book foundBook = bookService.getBookById("1");

        // Assert
        assertNull(foundBook);
        verify(bookRepository, times(1)).findById("1");
    }

    @Test
    void testGetAllBooks() {
        // Arrange
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book));

        // Act
        List<Book> books = bookService.getAllBooks();

        // Assert
        assertNotNull(books);
        assertEquals(1, books.size());
        assertEquals("Test Title", books.get(0).getTitle());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testDeleteBook() {
        // Arrange
        doNothing().when(bookRepository).deleteById("1");

        // Act
        bookService.deleteBook("1");

        // Assert
        verify(bookRepository, times(1)).deleteById("1");
    }

    @Test
    void testUpdateBook() {
        // Arrange
        Book updatedBook = new Book("1", "Updated Title", "Updated Author", "Updated Genre", "2024");
        when(bookRepository.findById("1")).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);

        // Act
        Book result = bookService.updateBook(updatedBook);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Title", result.getTitle());
        verify(bookRepository, times(1)).findById("1");
        verify(bookRepository, times(1)).save(updatedBook);
    }

    @Test
    void testUpdateBookNotFound() {
        // Arrange
        Book updatedBook = new Book("1", "Updated Title", "Updated Author", "Updated Genre", "2024");
        when(bookRepository.findById("1")).thenReturn(Optional.empty());

        // Act
        Book result = bookService.updateBook(updatedBook);

        // Assert
        assertNull(result);
        verify(bookRepository, times(1)).findById("1");
        verify(bookRepository, times(0)).save(any(Book.class));
    }
}
