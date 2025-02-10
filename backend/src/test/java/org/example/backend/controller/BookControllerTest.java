import org.example.backend.controller.BookController;
import org.example.backend.model.Book;
import org.example.backend.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @MockBean
    private BookService bookService; // Mock für BookService

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateBook() throws Exception {
        Book book = new Book(null, "Test Title", "Test Author", "Test Genre", "2023");

        // Mocking der BookService-Methode, die das Buch erstellt
        when(bookService.createBook(any(Book.class))).thenReturn(book);

        // Teste die POST-Anfrage und erwarte den Status 200 OK sowie das Buch im JSON-Format
        mockMvc.perform(MockMvcRequestBuilders.post("/api/book/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Test Title\",\"author\":\"Test Author\",\"genre\":\"Test Genre\",\"publicationYear\":\"2023\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.author").value("Test Author"))
                .andExpect(jsonPath("$.publicationYear").value("2023"));
    }
}
