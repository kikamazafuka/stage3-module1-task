package com.mjc.school.service;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.NewsDTO;
import org.modelmapper.ModelMapper;

public class NewsMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    private NewsMapper() {
    }

    public static NewsModel mapDTOToModel(NewsDTO newsDTO) {
        return modelMapper.map(newsDTO, NewsModel.class);
    }

    public static NewsDTO mapModelToDTO(NewsModel news) {
        return modelMapper.map(news, NewsDTO.class);
    }
}
