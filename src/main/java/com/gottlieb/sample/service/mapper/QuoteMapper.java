package com.gottlieb.sample.service.mapper;

import com.gottlieb.sample.domain.Quote;
import com.gottlieb.sample.service.dto.QuoteDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Quote} and its DTO {@link QuoteDTO}.
 */
@Mapper(componentModel = "spring")
public interface QuoteMapper extends EntityMapper<QuoteDTO, Quote> {}
