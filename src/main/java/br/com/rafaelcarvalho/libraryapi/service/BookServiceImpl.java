package br.com.rafaelcarvalho.libraryapi.service;

import br.com.rafaelcarvalho.libraryapi.api.exception.BusinessException;
import br.com.rafaelcarvalho.libraryapi.model.entity.Book;
import br.com.rafaelcarvalho.libraryapi.model.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        if(bookRepository.existsByIsbn(book.getIsbn())){
            throw new BusinessException("ISBN já cadastrado");
        }
        return bookRepository.save(book);
    }
}
