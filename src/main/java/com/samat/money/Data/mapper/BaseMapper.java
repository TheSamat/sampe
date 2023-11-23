package com.samat.money.Data.mapper;

import com.samat.money.Data.mapper.DefaultMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface BaseMapper<Entity, Element, Response, Request> {
    Element toElement(Entity entity);

    Response toResponse(Entity entity);

    Entity toEntity(Request request);

    void update(@MappingTarget Entity entity, Request request);
}
