package com.mjc.school.service;

import com.mjc.school.service.dto.NewsDTO;

import java.util.List;

public interface Service<T>{
    T createNews(T newsDTO);

    List<T> getAllNews();

    T getNewsById(Long newsId);

    T updateNews(T newsDTO);

    boolean deleteNews(Long newsId);
}
