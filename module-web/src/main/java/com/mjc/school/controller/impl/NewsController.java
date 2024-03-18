package com.mjc.school.controller.impl;

import com.mjc.school.controller.Controller;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.impl.NewsService;

import java.util.List;

public class NewsController implements Controller<NewsDTO> {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    public NewsDTO create(NewsDTO dto) {
        return newsService.createNews(dto);
    }

    @Override
    public List<NewsDTO> getAll() {
        return newsService.readAllNews();
    }

    @Override
    public NewsDTO getById(Long id) {
        return newsService.readByNewsId(id);
    }

    @Override
    public NewsDTO update(NewsDTO dto) {
        return newsService.updateNews(dto);
    }

    @Override
    public boolean delete(Long id) {
        return newsService.deleteNews(id);
    }
}
