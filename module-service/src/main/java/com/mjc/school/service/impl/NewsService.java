package com.mjc.school.service.impl;

import com.mjc.school.repository.GeneralRepository;
import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.NewsMapper;
import com.mjc.school.service.Service;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.exception.NewsServiceException;
import com.mjc.school.service.validator.NewsValidator;

import java.util.List;

public class NewsService implements Service<NewsDTO> {
    private final GeneralRepository<NewsModel> newsRepository;
    private final NewsValidator newsValidator;

    public NewsService() {
        this.newsRepository = new NewsRepository();
        newsValidator = new NewsValidator();
    }

    @Override
    public NewsDTO createNews(NewsDTO newsDTO) {
        List<String> validationErrors = newsValidator.validate(newsDTO);
        if (!validationErrors.isEmpty()) {
            throw new IllegalArgumentException("Validation failed: " + validationErrors);
        }
        try {
            NewsModel news = NewsMapper.mapDTOToModel(newsDTO);
            NewsModel createdNews = newsRepository.create(news);
            return NewsMapper.mapModelToDTO(createdNews);
        } catch (Exception e){
            throw new NewsServiceException("Error creating news", "CREATE_ERROR");
        }
    }

    @Override
    public List<NewsDTO> readAllNews() {
        try {
            return newsRepository.readAll().stream().map(NewsMapper::mapModelToDTO).toList();
        } catch (Exception e){
            throw new NewsServiceException("Error getting all news", "GET_ALL_ERROR");
        }

    }

    @Override
    public NewsDTO readByNewsId(Long newsId) {
        try {
            NewsModel news = newsRepository.readById(newsId);
            return NewsMapper.mapModelToDTO(news);
        } catch (Exception e){
            throw new NewsServiceException("Error getting news by id", "GET_BY_ID_ERROR");
        }

    }

    @Override
    public NewsDTO updateNews(NewsDTO newsDTO) {
        try {
            NewsModel existingNews = newsRepository.readById(newsDTO.getId());
            existingNews.setTitle(newsDTO.getTitle());
            existingNews.setContent(newsDTO.getContent());
            existingNews.setAuthorId(newsDTO.getAuthorId());

            NewsModel updatedNews = newsRepository.update(existingNews);
            return NewsMapper.mapModelToDTO(updatedNews);
        } catch (Exception e){
            throw new NewsServiceException("Error updating news", "UPDATE_ERROR");
        }

    }

    @Override
    public Boolean deleteNews(Long newsId) {
        try {
            NewsModel news = newsRepository.readById(newsId);
            if (news!=null) {
                newsRepository.deleteById(newsId);
                return true;
            }
            return false;
        } catch (Exception e){
            throw new NewsServiceException("Error deleting news", "DELETE_ERROR");
        }

    }

}
