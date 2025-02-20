package org.example.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backend.dto.BookUpdateDTO;
import org.example.backend.model.Book;
import org.example.backend.repo.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookRepository repo;

    @Test
    void getAllBooks_shouldReturnEmptyList_whenCalledInitially () throws Exception {
        //GIVEN
        //WHEN & THEN
        when(repo.findAll()).thenReturn(Collections.emptyList());
        mvc.perform(get("/api/book/getAll"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @Test
    void createBook_shouldReturnCreatedBook() throws Exception {
        // GIVEN
        Book book = new Book("1", "Test Title", "Test Author", "Fiction", "2023");
        when(repo.save(any(Book.class))).thenReturn(book);

        // WHEN & THEN
        mvc.perform(post("/api/book/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(book)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Title"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Test Author"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value("Fiction"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.publicationYear").value("2023"));
    }

    @Test
    void getBookById_shouldReturnBook_whenBookExists() throws Exception {
        // GIVEN
        Book book = new Book("1", "Test Title", "Test Author", "Fiction", "2023");
        when(repo.findById("1")).thenReturn(java.util.Optional.of(book));

        // WHEN & THEN
        mvc.perform(get("/api/book/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Title"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Test Author"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value("Fiction"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.publicationYear").value("2023"));
    }
    @Test
    void getBookById_shouldReturnNotFound_whenBookDoesNotExist() throws Exception {
        // GIVEN
        when(repo.findById("99")).thenReturn(java.util.Optional.empty());

        // WHEN & THEN
        mvc.perform(get("/api/book/99"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
    @Test
    void updateBook_shouldReturnUpdatedBook_whenBookExists() throws Exception {
        // GIVEN
        Book existingBook = new Book("1", "Old Title", "Old Author", "Fiction", "2020");
        BookUpdateDTO updateDTO = new BookUpdateDTO("New Title", "New Author", "Drama", "2023");

        // Stelle sicher, dass das Repository korrekt die alte Buch-ID zurückgibt
        when(repo.findById("1")).thenReturn(java.util.Optional.of(existingBook));

        // Stub das Save-Verfahren, um das aktualisierte Buch zurückzugeben
        when(repo.save(any(Book.class))).thenReturn(
                new Book("1", "New Title", "New Author", "Drama", "2023")
        );

        // WHEN & THEN
        mvc.perform(put("/api/book/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updateDTO))) // updateDTO als Request-Body
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("New Title"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("New Author"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value("Drama"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.publicationYear").value("2023"));
    }


    @Test
    void deleteBook_shouldReturnNoContent_whenBookExists() throws Exception {
        // GIVEN
        doNothing().when(repo).deleteById("1");

        // WHEN & THEN
        mvc.perform(delete("/api/book/delete/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}
