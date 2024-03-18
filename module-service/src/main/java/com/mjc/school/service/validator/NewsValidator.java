package com.mjc.school.service.validator;

import com.mjc.school.service.dto.NewsDto;

import java.util.ArrayList;
import java.util.List;

public class NewsValidator {
    public List<String> validate(NewsDto newsDTO) {
        List<String> errors = new ArrayList<>();

        if (newsDTO.getTitle() == null || newsDTO.getTitle().length() < 5 || newsDTO.getTitle().length() > 30) {
            errors.add("Title must be between 5 and 30 characters");
        }

        if (newsDTO.getContent() == null || newsDTO.getContent().length() < 5 || newsDTO.getContent().length() > 255) {
            errors.add("Content must be between 5 and 255 characters");
        }

        return errors;
    }
}
