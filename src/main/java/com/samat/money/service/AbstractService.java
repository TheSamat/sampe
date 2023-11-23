package com.samat.money.service;

import com.samat.money.exceprion.CustomError;
import com.samat.money.exceprion.CustomException;
import com.samat.money.mapper.DefaultMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractService<
        Entity, Index,
        Repository extends JpaRepository<Entity, Index>,
        Element, Response, Request,
        Mapper extends DefaultMapper<Entity, Element, Response, Request>> {

    abstract Repository getRepository();

    abstract Mapper getMapper();

    public List<Element> getList() {
        List<Entity> entities = getRepository().findAll();
        return entities.stream()
                .map(getMapper()::toElement)
                .collect(Collectors.toList());
    }

    public Page<Element> getAll(int page, int size) {
        Page<Entity> entities = getRepository().findAll(PageRequest.of(page, size));
        return entities.map(getMapper()::toElement);
    }

    public Response getById(Index id) {
        Optional<Entity> entity = getRepository().findById(id);
        if (entity.isPresent()) {
            return getMapper().toResponse(entity.get());
        } else {
            throw new CustomException(CustomError.ENTITY_NOT_FOUND);
        }
    }

    public Response create(@NonNull Request request) {
        Entity entity = getMapper().toEntity(request);
        Entity savedEntity = getRepository().save(entity);
        return getMapper().toResponse(savedEntity);
    }

    @Transactional
    public Response update(Index id, @NonNull Request request) {
        if (getRepository().existsById(id)) {
                Entity entity = getRepository().getById(id);
                getMapper().update(entity, request);

                Entity updatedEntity = getRepository().save(entity);
                return getMapper().toResponse(updatedEntity);
        } else {
            throw new CustomException(CustomError.ENTITY_NOT_FOUND);
        }
    }

    public void delete(Index id) {
        if (getRepository().existsById(id)) {
            getRepository().deleteById(id);
        } else {
            throw new CustomException(CustomError.ENTITY_NOT_FOUND);
        }
    }
}
