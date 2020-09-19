package com.cooldatasoft.converter;

import com.cooldatasoft.data.Book;
import com.cooldatasoft.entity.BookEntity;
import org.apache.camel.Converter;
import org.apache.camel.TypeConverters;

public class CustomConverters implements TypeConverters {

    @Converter
    public Book toBook(BookEntity bookEntity) {
        if (bookEntity == null) {
            throw new RuntimeException("bookEntity cannot be null!");
        }
        return new Book(bookEntity);
    }

    @Converter
    public BookEntity toBookEntity(Book book) {
        if (book == null) {
            throw new RuntimeException("book cannot be null!");
        }
        return new BookEntity(book);
    }
}