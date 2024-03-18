package com.mjc.school.controller;

import java.util.List;

public interface Controller <T>{
    T create(T dto);

    List<T> readAll();

    T readById(Long id);

    T update(T dto);

    Boolean delete(Long id);
}
