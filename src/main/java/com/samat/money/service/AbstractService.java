package com.samat.money.service;

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

@Slf4j
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
            throw new EntityNotFoundException("Entity with ID " + id + " not found");
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
            try {
                Entity entity = getRepository().getById(id);
                getMapper().update(entity, request);

                Entity updatedEntity = getRepository().save(entity);
                return getMapper().toResponse(updatedEntity);
            } catch (Exception e) {
                log.error("Error while updating entity with ID {}: {}", id, e.getMessage());
                throw new ServiceException("Error while updating entity", e);
            }
        } else {
            throw new EntityNotFoundException("Entity with ID " + id + " not found");
        }
    }

    public void delete(Index id) {
        if (getRepository().existsById(id)) {
            try {
                getRepository().deleteById(id);
            } catch (Exception e) {
                log.error("Error while deleting entity with ID {}: {}", id, e.getMessage());
                throw new ServiceException("Error while deleting entity", e);
            }
        } else {
            throw new EntityNotFoundException("Entity with ID " + id + " not found");
        }
    }

    protected static class ServiceException extends RuntimeException {
        public ServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    protected static class DataIntegrityException extends RuntimeException {
        public DataIntegrityException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    protected static class EntityNotFoundException extends RuntimeException {
        public EntityNotFoundException(String message) {
            super(message);
        }
    }
}
