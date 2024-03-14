package com.mjc.school.controller;

import com.mjc.school.service.dto.NewsDTO;

import java.util.List;
import java.util.Scanner;

public class NewsMenu {

    private final String NEWS_WITH_ID = "News with ID ";

    private final Controller<NewsDTO> newsController;
    private final Scanner scanner;

    public NewsMenu(Controller<NewsDTO> newsController) {
        this.newsController = newsController;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            System.out.println("Enter the number of operation:");
            System.out.println("1 - Get all news.");
            System.out.println("2 - Get news by id.");
            System.out.println("3 - Create news.");
            System.out.println("4 - Update news.");
            System.out.println("5 - Remove news by id.");
            System.out.println("0 - Exit.");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    getAllNews();
                    break;
                case 2:
                    getNewsById();
                    break;
                case 3:
                    createNews();
                    break;
                case 4:
                    updateNews();
                    break;
                case 5:
                    deleteNews();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 0 and 5.");
            }
        } while (choice != 0);
    }

    private void getAllNews() {
        System.out.println("All News:");
        List<NewsDTO> allNews = newsController.getAll();
        allNews.forEach(System.out::println);
    }

    private void getNewsById() {
        System.out.println("Enter news ID:");
        Long id = scanner.nextLong();
        NewsDTO newsDTO = newsController.getById(id);
        System.out.println(NEWS_WITH_ID + id + ": " + newsDTO);
    }

    private void createNews() {
        System.out.println("Enter news title:");
        String title = scanner.nextLine();
        System.out.println("Enter news content:");
        String content = scanner.nextLine();
        System.out.println("Enter author ID:");
        Long authorId = scanner.nextLong();

        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setTitle(title);
        newsDTO.setContent(content);
        newsDTO.setAuthorId(authorId);

        NewsDTO createdNews = newsController.create(newsDTO);
        System.out.println("Created News: " + createdNews);
    }

    private void updateNews() {
        System.out.println("Enter news ID to update:");
        Long id = scanner.nextLong();
        scanner.nextLine();

        NewsDTO existingNews = newsController.getById(id);
        if (existingNews != null) {
            System.out.println("Enter new title:");
            String title = scanner.nextLine();
            System.out.println("Enter new content:");
            String content = scanner.nextLine();
            System.out.println("Enter new author ID:");
            Long authorId = scanner.nextLong();

            NewsDTO newsDTO = new NewsDTO();
            newsDTO.setId(id);
            newsDTO.setTitle(title);
            newsDTO.setContent(content);
            newsDTO.setAuthorId(authorId);

            NewsDTO updatedNews = newsController.update(newsDTO);
            System.out.println("Updated News: " + updatedNews);
        } else {
            System.out.println(NEWS_WITH_ID + id + " not found!");
        }
    }

    private void deleteNews() {
        System.out.println("Enter news ID to delete:");
        Long id = scanner.nextLong();

        boolean deleted = newsController.delete(id);
        if (deleted) {
            System.out.println(NEWS_WITH_ID + id + " deleted successfully.");
        } else {
            System.out.println(NEWS_WITH_ID + id + " not found!");
        }
    }
}





