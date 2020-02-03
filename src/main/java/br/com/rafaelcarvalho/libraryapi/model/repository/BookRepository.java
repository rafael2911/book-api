package br.com.rafaelcarvalho.libraryapi.model.repository;

import br.com.rafaelcarvalho.libraryapi.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByIsbn(String isbn);

}
