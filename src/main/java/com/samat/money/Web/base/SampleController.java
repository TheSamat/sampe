package com.samat.money.Web.base;

import com.samat.money.Data.entity.SampleEntity;
import com.samat.money.Data.mapper.base.SampleMapper;
import com.samat.money.Domain.model.SampleElement;
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
public class SampleController extends BaseController<SampleEntity, Short, SampleRepository,
        SampleElement, SampleResponse, SampleRequest, SampleMapper> {

    SampleRepository repository;
    SampleMapper mapper;

    @Override
    public SampleRepository getRepository() {
        return repository;
    }

    @Override
    public SampleMapper getMapper() {
        return mapper;
    }
}
