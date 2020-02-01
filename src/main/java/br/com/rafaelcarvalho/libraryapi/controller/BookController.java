package br.com.rafaelcarvalho.libraryapi.controller;

import br.com.rafaelcarvalho.libraryapi.dto.BookDto;
import br.com.rafaelcarvalho.libraryapi.model.entity.Book;
import br.com.rafaelcarvalho.libraryapi.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody BookDto dto){

        Book entity = bookService.save(dto.converte());

        return ResponseEntity.created(null).body(new BookDto(entity));
    }

}
