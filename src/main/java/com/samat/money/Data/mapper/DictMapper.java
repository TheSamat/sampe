package com.samat.money.Data.mapper;

import com.samat.money.Data.mapper.DefaultMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface DictMapper<Entity, Response, Request> {
    Response toResponse(Entity entity);

    Entity toEntity(Request request);

    void update(@MappingTarget Entity entity, Request request);
}
