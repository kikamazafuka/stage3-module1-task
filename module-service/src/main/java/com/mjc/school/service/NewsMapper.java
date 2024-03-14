package com.mjc.school.service;

import com.mjc.school.repository.model.News;
import com.mjc.school.service.dto.NewsDTO;
import org.modelmapper.ModelMapper;

public class NewsMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    private NewsMapper() {
    }

    public static News mapDTOToModel(NewsDTO newsDTO) {
        return modelMapper.map(newsDTO, News.class);
    }

    public static NewsDTO mapModelToDTO(News news) {
        return modelMapper.map(news, NewsDTO.class);
    }
}
