package com.cooldatasoft.repository;

import com.cooldatasoft.data.Book;
import com.cooldatasoft.entity.BookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<BookEntity, String>, QuerydslPredicateExecutor<BookEntity> {

}
