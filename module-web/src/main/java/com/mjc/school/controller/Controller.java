package com.mjc.school.controller;

import java.util.List;

public interface Controller <T>{
    T create(T dto);

    List<T> getAll();

    T getById(Long id);

    T update(T dto);

    boolean delete(Long id);
}
