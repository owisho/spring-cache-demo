package com.example.springcachedemo.caching;

public interface BookRepository {
    Book getByIsbn(String isbn);
}
