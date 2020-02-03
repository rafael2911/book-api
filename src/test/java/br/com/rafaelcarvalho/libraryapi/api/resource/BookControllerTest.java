package br.com.rafaelcarvalho.libraryapi.api.resource;

import br.com.rafaelcarvalho.libraryapi.api.dto.BookDto;
import br.com.rafaelcarvalho.libraryapi.api.exception.BusinessException;
import br.com.rafaelcarvalho.libraryapi.model.entity.Book;
import br.com.rafaelcarvalho.libraryapi.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BookControllerTest {

    public static final String API_BOOKS = "/api/books";
    public static final String ISBN_JA_CADASTRADO = "ISBN já cadastrado";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("Deve criar um livro com sucesso.")
    public void createBookTest() throws Exception {
        BookDto dto = createBookDto();
        Book savedBook = createBook();

        BDDMockito.given(bookService.save(Mockito.any(Book.class))).willReturn(savedBook);

        String json = new ObjectMapper().writeValueAsString(dto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(API_BOOKS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("id").value(101L))
                .andExpect(jsonPath("title").value(dto.getTitle()))
                .andExpect(jsonPath("author").value(dto.getAuthor()))
                .andExpect(jsonPath("isbn").value(dto.getIsbn()));


    }

    @Test
    @DisplayName("Deve lançar erro de validação quando não houver dados suficientes para criação do livro.")
    public void createInvalidBookTest() throws Exception {
        //cenario
        String json = new ObjectMapper().writeValueAsString(new BookDto());

        //execucao
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(API_BOOKS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        //verificacao
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("errors", hasSize(3)));
    }

    @Test
    @DisplayName("Deve lançar erro ao tentar cadastrar um livro com isbn já utilizado por outro.")
    public void createBookWithDuplicatedIsbn() throws Exception{
        // cenario
        String json = new ObjectMapper().writeValueAsString(createBookDto());
        BDDMockito.given(bookService.save(Mockito.any(Book.class))).willThrow(new BusinessException(ISBN_JA_CADASTRADO));

        //execucao
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(API_BOOKS)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        //verificacao
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("errors", hasSize(1)))
                .andExpect(jsonPath("errors[0]").value(ISBN_JA_CADASTRADO));
    }

    /* ############# PRIVATE METHODS ############# */
    private BookDto createBookDto() {
        return BookDto.builder()
                .title("As aventuras")
                .author("Rafael")
                .isbn("123456")
                .build();
    }

    private Book createBook() {
        return Book.builder()
                .id(101L)
                .title("As aventuras")
                .author("Rafael")
                .isbn("123456")
                .build();
    }

}
