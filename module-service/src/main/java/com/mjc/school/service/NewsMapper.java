package com.mjc.school.service;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsDto;
import org.modelmapper.ModelMapper;

public class NewsMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    private NewsMapper() {
    }

    public static NewsModel mapDTOToModel(NewsDto newsDTO) {
        return modelMapper.map(newsDTO, NewsModel.class);
    }

    public static NewsDto mapModelToDTO(NewsModel news) {
        return modelMapper.map(news, NewsDto.class);
    }
}
