package com.mjc.school.repository.impl;

import com.mjc.school.repository.GeneralRepository;
import com.mjc.school.repository.datasource.DataSource;
import com.mjc.school.repository.model.NewsModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NewsRepository implements GeneralRepository<NewsModel> {

    private final DataSource dataSource;
    private final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    private final List<NewsModel> allNews;

    public NewsRepository() {
        this.dataSource = DataSource.getInstance();
        this.allNews = dataSource.generateNews();
    }


    @Override
    public List<NewsModel> readAll() {
        return this.allNews;
    }

    @Override
    public NewsModel readById(Long id) {
        for (NewsModel news : allNews) {
            if (news.getId() == id) {
                return news;
            }
        }
        return null;
    }

    @Override
    public NewsModel create(NewsModel news) {
        LocalDateTime time = LocalDateTime.parse(LocalDateTime.now().format(TIME_FORMAT));
        Long id = (long) allNews.size() + 1;
        news.setId(id);
        news.setCreateDate(time);
        news.setLastUpdateDate(time);
        allNews.add(news);
        return news;
    }

    @Override
    public NewsModel update(NewsModel news) {
        LocalDateTime time = LocalDateTime.parse(LocalDateTime.now().format(TIME_FORMAT));
        NewsModel existingNews = readById(news.getId());
        if (existingNews != null) {
            existingNews.setTitle(news.getTitle());
            existingNews.setContent(news.getContent());
            existingNews.setLastUpdateDate(time);
            existingNews.setAuthorId(news.getAuthorId());
            allNews.replaceAll(n -> n.getId().equals(news.getId()) ? existingNews : n);
        }
        return existingNews;
    }

    @Override
    public Boolean deleteById(Long id) {
        NewsModel newsToDelete = readById(id);
        if (newsToDelete!=null){
            allNews.remove(newsToDelete);
            return true;
        }
        return false;
    }
}
