package br.com.rafaelcarvalho.libraryapi.api.dto;

import br.com.rafaelcarvalho.libraryapi.model.entity.Book;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String author;

    @NotEmpty
    private String isbn;

    public BookDto(Book entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.isbn = entity.getIsbn();
    }

    public Book converte() {
        return Book.builder()
                .title(this.title)
                .author(this.author)
                .isbn(this.isbn)
                .build();
    }
}
