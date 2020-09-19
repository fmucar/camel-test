package com.cooldatasoft.converter;

import com.cooldatasoft.data.Book;
import com.cooldatasoft.entity.BookEntity;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CustomConvertersTest {

    CustomConverters customConverters = new CustomConverters();

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenInputBookEntityIsNull() {
        customConverters.toBook(null);
    }

    @Test
    public void shouldConvertSuccessfullyBookEntityToBook1() {

        Book book = customConverters.toBook(new BookEntity());

        assertThat(book, is(notNullValue()));
        assertThat(book.getId(), is(nullValue()));
        assertThat(book.getAuthor(), is(nullValue()));
        assertThat(book.getTitle(), is(nullValue()));
    }

    @Test
    public void shouldConvertSuccessfullyBookEntityToBook2() {

        Book book = customConverters.toBook(new BookEntity("id", "title", "author"));

        assertThat(book, is(notNullValue()));
        assertThat(book.getId(), is("id"));
        assertThat(book.getAuthor(), is("author"));
        assertThat(book.getTitle(), is("title"));
    }


    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenInputBookIsNull() {
        customConverters.toBookEntity(null);
    }

    @Test
    public void shouldConvertSuccessfullyBookToBookEntity1() {

        BookEntity bookEntity = customConverters.toBookEntity(new Book());

        assertThat(bookEntity, is(notNullValue()));
        assertThat(bookEntity.getI(), is(nullValue()));
        assertThat(bookEntity.getA(), is(nullValue()));
        assertThat(bookEntity.getT(), is(nullValue()));
    }

    @Test
    public void shouldConvertSuccessfullyBookToBookEntity2() {

        BookEntity bookEntity = customConverters.toBookEntity(new Book("id", "title", "author"));

        assertThat(bookEntity, is(notNullValue()));
        assertThat(bookEntity.getI(), is("id"));
        assertThat(bookEntity.getA(), is("author"));
        assertThat(bookEntity.getT(), is("title"));
    }
}