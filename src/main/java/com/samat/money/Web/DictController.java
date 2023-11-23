package com.samat.money.Web;

import com.samat.money.Domain.constant.Error;
import com.samat.money.Application.exceprion.CustomException;
import com.samat.money.Data.mapper.DictMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CacheConfig(cacheNames = "#{T(com.yourpackage.YourClass).getSimpleName()}")
public abstract class DictController<
        Entity, Index,
        Repository extends JpaRepository<Entity, Index>,
        Response, Request,
        Mapper extends DictMapper<Entity, Response, Request>> {

    public abstract Repository getRepository();
    public abstract Logger getLogger();
    public abstract Mapper getMapper();

    @GetMapping("/list")
    @Cacheable
    public List<Response> getList() {
        List<Entity> entities = getRepository().findAll();
        return entities.stream()
                .map(getMapper()::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/")
    public Page<Response> getAll(@RequestParam(defaultValue = "0") @Min(0) int page,
                                @RequestParam(defaultValue = "25") @Min(1) @Max(100) int size) {
        Page<Entity> entities = getRepository().findAll(PageRequest.of(page, size));
        return entities.map(getMapper()::toResponse);
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable Index id) {
        Optional<Entity> entity = getRepository().findById(id);
        if (entity.isPresent()) {
            return getMapper().toResponse(entity.get());
        } else {
            throw new CustomException(Error.ENTITY_NOT_FOUND, getLogger());
        }
    }

    @PostMapping("/")
    @CacheEvict(allEntries = true)
    public Response create(@Valid @RequestBody Request request) {
        Entity entity = getMapper().toEntity(request);
        Entity savedEntity = getRepository().save(entity);
        return getMapper().toResponse(savedEntity);
    }

    @PutMapping("/{id}")
    @CacheEvict(allEntries = true)
    public Response update(@PathVariable Index id, @Valid @RequestBody Request request) {
        if (getRepository().existsById(id)) {
            Entity entity = getRepository().getById(id);
            getMapper().update(entity, request);

            Entity updatedEntity = getRepository().save(entity);
            return getMapper().toResponse(updatedEntity);
        } else {
            throw new CustomException(Error.ENTITY_NOT_FOUND, getLogger());
        }
    }

    @DeleteMapping("/{id}")
    @CacheEvict(allEntries = true)
    public void delete(@PathVariable Index id) {
        if (getRepository().existsById(id)) {
            getRepository().deleteById(id);
        } else {
            throw new CustomException(Error.ENTITY_NOT_FOUND, getLogger());
        }
    }
}
