package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private static final DataSource instance = new DataSource();

    private DataSource() {
    }

    public static DataSource getInstance() {
        return instance;
    }

    public List<NewsModel> generateNews() {
        List<NewsModel> newsList = new ArrayList<>();

        List<String> authorsData = readFromFile("author.txt");
        List<String> contentData = readFromFile("content.txt");

        for (int i = 1; i <= 20; i++) {
            AuthorModel author = createAuthor(authorsData.get(i % authorsData.size()));
            String content = contentData.get(i % contentData.size());

            NewsModel news = new NewsModel((long) i, "Title " + i, content, LocalDateTime.now(), LocalDateTime.now(), author.getId());
            newsList.add(news);
        }

        return newsList;
    }

    private static AuthorModel createAuthor(String authorData) {
        if (authorData != null) {
            return new AuthorModel(authorData);
        } else {
            throw new IllegalArgumentException("Invalid author data: " + authorData);
        }
    }

    private static List<String> readFromFile(String fileName) {
        try {
            Path filePath = Paths.get(DataSource.class.getClassLoader().getResource(fileName).toURI());
            return Files.readAllLines(filePath);
        } catch (IOException | NullPointerException | java.net.URISyntaxException e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
    }
}
