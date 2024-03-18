package com.mjc.school.controller.impl;

import com.mjc.school.controller.Controller;
import com.mjc.school.service.Service;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.impl.NewsService;

import java.util.List;

public class NewsController implements Controller<NewsDto> {

    private final Service<NewsDto> newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    public NewsDto create(NewsDto dto) {
        return newsService.createNews(dto);
    }

    @Override
    public List<NewsDto> readAll() {
        return newsService.readAllNews();
    }

    @Override
    public NewsDto readById(Long id) {
        return newsService.readByNewsId(id);
    }

    @Override
    public NewsDto update(NewsDto dto) {
        return newsService.updateNews(dto);
    }

    @Override
    public Boolean delete(Long id) {
        return newsService.deleteNews(id);
    }
}
