package com.mjc.school.repository.impl;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.datasource.DataGenerator;
import com.mjc.school.repository.model.News;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NewsRepository implements Repository<News> {

    private final DataGenerator dataGenerator;
    private final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    private final List<News> allNews;

    public NewsRepository() {
        this.dataGenerator = DataGenerator.getInstance();
        this.allNews = dataGenerator.generateNews();
    }


    @Override
    public List<News> findAll() {
        return this.allNews;
    }

    @Override
    public News findById(long id) {
        for (News news : allNews) {
            if (news.getId() == id) {
                return news;
            }
        }
        return null;
    }

    @Override
    public News create(News news) {
        LocalDateTime time = LocalDateTime.parse(LocalDateTime.now().format(TIME_FORMAT));
        Long id = (long) allNews.size() + 1;
        news.setId(id);
        news.setCreateDate(time);
        news.setLastUpdateDate(time);
        allNews.add(news);
        return news;
    }

    @Override
    public News update(News news) {
        LocalDateTime time = LocalDateTime.parse(LocalDateTime.now().format(TIME_FORMAT));
        News existingNews = findById(news.getId());
        if (existingNews != null) {
            existingNews.setTitle(news.getTitle());
            existingNews.setContent(news.getContent());
            existingNews.setLastUpdateDate(time);
            existingNews.setAuthorId(news.getAuthorId());
            allNews.replaceAll(n -> n.getId() == news.getId() ? existingNews : n);
        }
        return existingNews;
    }

    @Override
    public Boolean deleteById(long id) {
        News newsToDelete = findById(id);
        if (newsToDelete!=null){
            allNews.remove(newsToDelete);
            return true;
        }
        return false;
    }
}
