package com.samat.money.mapper;

import org.mapstruct.MappingTarget;


public interface DefaultMapper<Entity, Element, Response, Request> {
    Element toElement(Entity entity);

    Response toResponse(Entity entity);

    Entity toEntity(Request request);

    void update(@MappingTarget Entity entity, Request request);
}
