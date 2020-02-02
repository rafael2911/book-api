package br.com.rafaelcarvalho.libraryapi.service;

import br.com.rafaelcarvalho.libraryapi.model.entity.Book;
import br.com.rafaelcarvalho.libraryapi.model.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {

    public static final String TITLE = "As aventuras";
    public static final String AUTHOR = "Fulano";
    public static final String ISBN = "123";

    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp(){
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    @DisplayName("Deve salvar um livro")
    public void saveBookTest(){

        //cenario
        Book book = Book.builder()
                .title(TITLE)
                .author(AUTHOR)
                .isbn(ISBN).build();

        when(bookRepository.save(book))
                .thenReturn(Book.builder().id(1L).title(TITLE).author(AUTHOR).isbn(ISBN).build());

        //execucao
        Book savedBook = this.bookService.save(book);

        //verificacao
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo(TITLE);
        assertThat(savedBook.getAuthor()).isEqualTo(AUTHOR);
        assertThat(savedBook.getIsbn()).isEqualTo(ISBN);
    }

}
