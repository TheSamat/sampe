package com.samat.money.Web.dict;

import com.samat.money.Data.entity.SampleEntity;
import com.samat.money.Data.mapper.dict.DictSampleMapper;
import com.samat.money.Domain.model.SampleRequest;
import com.samat.money.Domain.model.SampleResponse;
import com.samat.money.Data.repository.SampleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class DictSampleController extends DictController<SampleEntity, Short, SampleRepository,
        SampleResponse, SampleRequest, DictSampleMapper> {

    SampleRepository repository;
    DictSampleMapper mapper;

    @Override
    public SampleRepository getRepository() {
        return repository;
    }

    @Override
    public DictSampleMapper getMapper() {
        return mapper;
    }
}
