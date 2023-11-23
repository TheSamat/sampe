package com.samat.money.Web.dict;

import com.samat.money.Data.mapper.DefaultMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {
                DefaultMapper.class,
        }
)
public interface DictMapper<Entity, Response, Request> {
    Response toResponse(Entity entity);

    Entity toEntity(Request request);

    void update(@MappingTarget Entity entity, Request request);
}
