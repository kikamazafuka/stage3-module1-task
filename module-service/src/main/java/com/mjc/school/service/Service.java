package com.mjc.school.service;

import java.util.List;

public interface Service<T>{
    T createNews(T newsDTO);

    List<T> readAllNews();

    T readNewsById(Long newsId);

    T updateNews(T newsDTO);

    Boolean deleteNews(Long newsId);
}
