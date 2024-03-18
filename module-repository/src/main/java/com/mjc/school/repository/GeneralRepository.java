package com.mjc.school.repository;

import java.util.List;

public interface GeneralRepository<T>{
    List<T> readAll();
    T readById(Long id);
    T create(T t);
    T update(T t);
    Boolean deleteById(Long id);
}
