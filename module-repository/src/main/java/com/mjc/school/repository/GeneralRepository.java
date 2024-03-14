package com.mjc.school.repository;

import java.util.List;

public interface GeneralRepository<T>{
    List<T> readAll();
    T readById(long id);
    T create(T t);
    T update(T t);
    Boolean deleteById(long id);
}
