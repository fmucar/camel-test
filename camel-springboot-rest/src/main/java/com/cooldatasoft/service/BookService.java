
package com.cooldatasoft.service;

import com.cooldatasoft.data.Book;
import com.cooldatasoft.entity.BookEntity;
import com.cooldatasoft.entity.QBookEntity;
import com.cooldatasoft.repository.BookRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book findBook(String id) {
        BooleanExpression idExpression = QBookEntity.bookEntity.i.eq(id);
        return bookRepository.findOne(idExpression).map(Book::new).orElse(null);
    }

    public Collection<Book> findBooks() {
        return bookRepository.findAll().stream()
                .map(Book::new)
                .collect(Collectors.toList());
    }

    public BookEntity updateBook(Book book) {
        if (book.getId() == null) {
            throw new RuntimeException("Id cannot be empty for updated object");
        }
        return bookRepository.save(new BookEntity(book));
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    public BookEntity createBook(Book book) {
        return bookRepository.save(new BookEntity(book));
    }

    public BookEntity createBook(BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

}
