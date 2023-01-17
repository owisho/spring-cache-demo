package com.example.spring.caching;

public interface BookRepository {
    Book getByIsbn(String isbn);
}
