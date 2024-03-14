package com.mjc.school.service.validator;

public class NewsValidator {
    private static NewsValidator newsValidator;
    private static final String NEWS_ID = "News id";
    private static final String NEWS_CONTENT = "News content";
    private static final String AUTHOR_ID = "Author id";
    private static final String NEWS_TITLE = "News title";
    private static final Integer NEWS_CONTENT_MIN_LENGTH = 5;
    private static final Integer NEWS_CONTENT_MAX_LENGTH = 255;
    private static final Integer NEWS_TITLE_MIN_LENGTH = 5;
    private static final Integer NEWS_TITLE_MAX_LENGTH = 30;
    private static final Integer MAX_AUTHOR_ID = 20;

    public NewsValidator() {
    }

    public static NewsValidator getNewsValidator() {
        if (newsValidator == null) {
            newsValidator = new NewsValidator();
        }

        return newsValidator;
    }
}
