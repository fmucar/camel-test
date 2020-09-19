package com.cooldatasoft.entity;

import com.cooldatasoft.data.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookEntity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {

    @Id
    private String i;
    @Indexed
    private String t;
    @Indexed
    private String a;

    public BookEntity(Book book) {
        this.i = book.getId();
        this.t = book.getTitle();
        this.a = book.getAuthor();
    }
}
