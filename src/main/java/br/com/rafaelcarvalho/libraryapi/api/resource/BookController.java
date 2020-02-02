package br.com.rafaelcarvalho.libraryapi.api.resource;

import br.com.rafaelcarvalho.libraryapi.dto.BookDto;
import br.com.rafaelcarvalho.libraryapi.model.entity.Book;
import br.com.rafaelcarvalho.libraryapi.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/books")
public class BookController {

    private BookService bookService;

    private ModelMapper mapper;

    public BookController(BookService bookService, ModelMapper mapper) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody BookDto dto){

        Book entity = mapper.map(dto, Book.class);
        entity = bookService.save(entity);
        return ResponseEntity.created(null)
                .body(mapper.map(entity, BookDto.class));
    }

}
