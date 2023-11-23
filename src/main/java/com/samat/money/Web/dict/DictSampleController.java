package com.samat.money.Web.dict;

import com.samat.money.Data.entity.SampleEntity;
import com.samat.money.Data.mapper.dict.DictSampleMapper;
import com.samat.money.Domain.model.SampleRequest;
import com.samat.money.Domain.model.SampleResponse;
import com.samat.money.Data.repository.SampleRepository;
import com.samat.money.Web.DictController;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sample_dict")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Slf4j
public class DictSampleController extends DictController<SampleEntity, Short, SampleRepository,
        SampleResponse, SampleRequest, DictSampleMapper> {

    SampleRepository repository;
    DictSampleMapper mapper;

    @Override
    public SampleRepository getRepository() {
        return repository;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public DictSampleMapper getMapper() {
        return mapper;
    }
}
