package com.cooldatasoft.service;

import com.cooldatasoft.data.Book;
import com.cooldatasoft.entity.BookEntity;
import com.cooldatasoft.repository.BookRepository;
import com.querydsl.core.types.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepositoryMock;

    @Test
    public void shouldCallFindBooksAndSUccessfullyReturnResult() {

        BookEntity bookEntity = new BookEntity("1", "title", "author");
        given(bookRepositoryMock.findAll()).willReturn(Collections.singletonList(bookEntity));


        Collection<Book> books = bookService.findBooks();

        assertThat(books, is(notNullValue()));
        assertThat(books, hasSize(1));
        Book book = books.iterator().next();
        assertThat(book, is(notNullValue()));
        assertThat(book.getId(), is(bookEntity.getI()));
        assertThat(book.getAuthor(), is(bookEntity.getA()));
        assertThat(book.getTitle(), is(bookEntity.getT()));
    }


    @Test
    public void shouldCallFindBookAndSUccessfullyReturnResult() {

        BookEntity bookEntity = new BookEntity("1", "title", "author");
        given(bookRepositoryMock.findOne(ArgumentMatchers.any(Predicate.class))).willReturn(Optional.of(bookEntity));

        Book book = bookService.findBook("1");

        assertThat(book, is(notNullValue()));
        assertThat(book.getId(), is(bookEntity.getI()));
        assertThat(book.getAuthor(), is(bookEntity.getA()));
        assertThat(book.getTitle(), is(bookEntity.getT()));
    }


    @Test
    public void shouldCallUpdateBookAndSUccessfullyReturnResult() {

        BookEntity bookEntity = new BookEntity("1", "title", "author");
        given(bookRepositoryMock.save(ArgumentMatchers.any(BookEntity.class))).willReturn(bookEntity);

        BookEntity bookEntityUpdated = bookService.updateBook(new Book(bookEntity));

        assertThat(bookEntityUpdated, is(notNullValue()));
        assertThat(bookEntityUpdated.getI(), is(bookEntity.getI()));
        assertThat(bookEntityUpdated.getA(), is(bookEntity.getA()));
        assertThat(bookEntityUpdated.getT(), is(bookEntity.getT()));
    }


    @Test
    public void shouldCallDeleteBookAndSUccessfullyReturnResult() {
        bookService.deleteBook("id");

        verify(bookRepositoryMock).deleteById(ArgumentMatchers.any());
    }

    @Test
    public void shouldCallCreateBookAndSUccessfullyReturnResult() {

        BookEntity bookEntity = new BookEntity("1", "title", "author");
        given(bookRepositoryMock.save(ArgumentMatchers.any(BookEntity.class))).willReturn(bookEntity);

        BookEntity bookEntityCreated = bookService.createBook(new Book(bookEntity));

        assertThat(bookEntityCreated, is(notNullValue()));
        assertThat(bookEntityCreated.getI(), is(bookEntity.getI()));
        assertThat(bookEntityCreated.getA(), is(bookEntity.getA()));
        assertThat(bookEntityCreated.getT(), is(bookEntity.getT()));
    }
}