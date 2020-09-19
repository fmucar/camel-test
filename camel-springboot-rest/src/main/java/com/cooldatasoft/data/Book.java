package com.cooldatasoft.data;

import com.cooldatasoft.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private String id;
    private String title;
    private String author;

    public Book(BookEntity bookEntity) {
        this.id = bookEntity.getI();
        this.title = bookEntity.getT();
        this.author = bookEntity.getA();
    }
}
