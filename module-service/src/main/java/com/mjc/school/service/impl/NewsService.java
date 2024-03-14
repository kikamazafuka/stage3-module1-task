package com.mjc.school.service.impl;

import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.News;
import com.mjc.school.service.NewsMapper;
import com.mjc.school.service.Service;
import com.mjc.school.service.dto.NewsDTO;
import com.mjc.school.service.exception.NewsServiceException;

import java.util.List;
import java.util.stream.Collectors;

public class NewsService implements Service<NewsDTO> {
    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = new NewsRepository();
    }

    @Override
    public NewsDTO createNews(NewsDTO newsDTO) {
        try {
            News news = NewsMapper.mapDTOToModel(newsDTO);
            News createdNews = newsRepository.create(news);
            return NewsMapper.mapModelToDTO(createdNews);
        } catch (Exception e){
            throw new NewsServiceException("Error creating news", "CREATE_ERROR");
        }
    }

    @Override
    public List<NewsDTO> getAllNews() {
        try {
            List<News> all = newsRepository.findAll();
            return newsRepository.findAll().stream().map(NewsMapper::mapModelToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e){
            throw new NewsServiceException("Error getting all news", "GET_ALL_ERROR");
        }

    }

    @Override
    public NewsDTO getNewsById(Long newsId) {
        try {
            News news = newsRepository.findById(newsId);
            return NewsMapper.mapModelToDTO(news);
        } catch (Exception e){
            throw new NewsServiceException("Error getting news by id", "GET_BY_ID_ERROR");
        }

    }

    @Override
    public NewsDTO updateNews(NewsDTO newsDTO) {
        try {
            News existingNews = newsRepository.findById(newsDTO.getId());
            existingNews.setTitle(newsDTO.getTitle());
            existingNews.setContent(newsDTO.getContent());
            existingNews.setAuthorId(newsDTO.getAuthorId());

            News updatedNews = newsRepository.update(existingNews);
            return NewsMapper.mapModelToDTO(updatedNews);
        } catch (Exception e){
            throw new NewsServiceException("Error updating news", "UPDATE_ERROR");
        }

    }

    @Override
    public boolean deleteNews(Long newsId) {
        try {
            News news = newsRepository.findById(newsId);
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
