package br.com.rafaelcarvalho.libraryapi.controller;

import br.com.rafaelcarvalho.libraryapi.dto.BookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/books")
public class BookController {

    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody BookDto dto){

        dto.setId(1L);

        return ResponseEntity.created(null).body(dto);
    }

}
