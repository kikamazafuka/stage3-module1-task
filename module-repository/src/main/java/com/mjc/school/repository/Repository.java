package com.mjc.school.repository;

import java.util.List;

public interface Repository <T>{
    List<T> findAll();
    T findById(long id);
    T create(T t);
    T update(T t);
    Boolean deleteById(long id);
}
