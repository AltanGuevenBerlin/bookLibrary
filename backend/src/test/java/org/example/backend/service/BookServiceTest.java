package org.example.backend.service;

import org.example.backend.dto.BookUpdateDTO;
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
        Book existingBook = new Book("1", "Old Title", "Old Author", "Old Genre", "2020");
        BookUpdateDTO updateDTO = new BookUpdateDTO("Updated Title", "Updated Author", "Updated Genre", "2024");

        // Stelle sicher, dass das Repository das vorhandene Buch zurückgibt
        when(bookRepository.findById("1")).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenReturn(new Book("1", "Updated Title", "Updated Author", "Updated Genre", "2024"));

        // Act
        Book result = bookService.updateBook("1", updateDTO);  // Passe hier die Argumente an

        // Assert
        assertNotNull(result);
        assertEquals("Updated Title", result.getTitle());
        assertEquals("Updated Author", result.getAuthor());
        assertEquals("Updated Genre", result.getGenre());
        assertEquals("2024", result.getPublicationYear());
        verify(bookRepository, times(1)).findById("1");
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void testUpdateBookNotFound() {
        // Arrange
        BookUpdateDTO updateDTO = new BookUpdateDTO("Updated Title", "Updated Author", "Updated Genre", "2024");

        // Das Buch existiert nicht
        when(bookRepository.findById("1")).thenReturn(Optional.empty());

        // Act
        Book result = bookService.updateBook("1", updateDTO);

        // Assert
        assertNull(result);
        verify(bookRepository, times(1)).findById("1");
        verify(bookRepository, times(0)).save(any(Book.class));
    }

}
