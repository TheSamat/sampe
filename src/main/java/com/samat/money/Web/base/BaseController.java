package com.samat.money.Web.base;

import com.samat.money.Application.exceprion.CustomError;
import com.samat.money.Application.exceprion.CustomException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/base")
@RequiredArgsConstructor
public abstract class BaseController<
        Entity, Index,
        Repository extends JpaRepository<Entity, Index>,
        Element, Response, Request,
        Mapper extends BaseMapper<Entity, Element, Response, Request>> {

    public abstract Repository getRepository();

    public abstract Mapper getMapper();

    @GetMapping("/list")
    public List<Element> getList() {
        List<Entity> entities = getRepository().findAll();
        return entities.stream()
                .map(getMapper()::toElement)
                .collect(Collectors.toList());
    }

    @GetMapping("/")
    public Page<Element> getAll(int page, int size) {
        Page<Entity> entities = getRepository().findAll(PageRequest.of(page, size));
        return entities.map(getMapper()::toElement);
    }

    @GetMapping("/{id}")
    public Response getById(Index id) {
        Optional<Entity> entity = getRepository().findById(id);
        if (entity.isPresent()) {
            return getMapper().toResponse(entity.get());
        } else {
            throw new CustomException(CustomError.ENTITY_NOT_FOUND);
        }
    }

    @PostMapping("/")
    public Response create(@NonNull Request request) {
        Entity entity = getMapper().toEntity(request);
        Entity savedEntity = getRepository().save(entity);
        return getMapper().toResponse(savedEntity);
    }

    @PutMapping("/{id}")
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

    @DeleteMapping("/{id}")
    public void delete(Index id) {
        if (getRepository().existsById(id)) {
            getRepository().deleteById(id);
        } else {
            throw new CustomException(CustomError.ENTITY_NOT_FOUND);
        }
    }
}
