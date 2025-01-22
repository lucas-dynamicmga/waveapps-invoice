package com.gottlieb.sample.service.mapper;

import com.gottlieb.sample.domain.MessageEntity;
import com.gottlieb.sample.service.dto.MessageDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageDTO toDto(MessageEntity entity);
    MessageEntity toEntity(MessageDTO dto);
}
