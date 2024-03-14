package com.mjc.school;

import com.mjc.school.controller.NewsMenu;
import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.service.impl.NewsService;


public class NewsMain {

    public static void main(String[] args) {

        NewsService newsService = new NewsService();
        NewsController newsController = new NewsController(newsService);
        NewsMenu newsMenu = new NewsMenu(newsController);
        newsMenu.start();
    }

}

