package com.mjc.school.repository.datasource;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataGenerator {

    private static final DataGenerator instance = new DataGenerator();

    private DataGenerator() {
    }

    public static DataGenerator getInstance() {
        return instance;
    }

    public List<News> generateNews() {
        List<News> newsList = new ArrayList<>();

        List<String> authorsData = readFromFile("author.txt");
        List<String> contentData = readFromFile("content.txt");

        for (int i = 1; i <= 20; i++) {
            Author author = createAuthor(authorsData.get(i % authorsData.size()));
            String content = contentData.get(i % contentData.size());

            News news = new News((long) i, "Title " + i, content, LocalDateTime.now(), LocalDateTime.now(), author.getId());
            newsList.add(news);
        }

        return newsList;
    }

    private static Author createAuthor(String authorData) {
        if (authorData != null) {
            return new Author(authorData);
        } else {
            throw new IllegalArgumentException("Invalid author data: " + authorData);
        }
    }

    private static List<String> readFromFile(String fileName) {
        try {
            Path filePath = Paths.get(DataGenerator.class.getClassLoader().getResource(fileName).toURI());
            return Files.readAllLines(filePath);
        } catch (IOException | NullPointerException | java.net.URISyntaxException e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
    }
}
