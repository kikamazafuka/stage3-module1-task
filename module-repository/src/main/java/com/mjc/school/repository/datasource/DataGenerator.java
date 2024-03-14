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

    private static DataGenerator instance;

    private DataGenerator() {
    }

    public static DataGenerator getInstance() {
        if (instance == null) {
            synchronized (DataGenerator.class) {
                if (instance == null) {
                    instance = new DataGenerator();
                }
            }
        }
        return instance;
    }

    public List<News> generateNews() {
        List<News> newsList = new ArrayList<>();

        // Read data from files
        List<String> authorsData = readFromFile("author.txt");
        List<String> contentData = readFromFile("content.txt");

        // Generate 20 news items
        for (int i = 1; i <= 20; i++) {
            Author author = createAuthor(authorsData.get(i % authorsData.size()));
            String content = contentData.get(i % contentData.size());

            News news = new News((long)i, "Title " + i, content, LocalDateTime.now(), LocalDateTime.now(), author.getId());
            newsList.add(news);
        }

        return newsList;
    }

    private static Author createAuthor(String authorData) {
//        String[] authorInfo = authorData.split(",");
//        if (authorInfo.length == 2) {
//            return new Author(Long.parseLong(authorInfo[0]), authorInfo[1]);
//        } else {
//            throw new IllegalArgumentException("Invalid author data: " + authorData);
//        }

        if (authorData!=null) {
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
