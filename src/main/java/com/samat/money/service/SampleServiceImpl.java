package com.samat.money.service;

import com.samat.money.entity.SampleEntity;
import com.samat.money.mapper.SampleMapper;
import com.samat.money.model.SampleElement;
import com.samat.money.model.SampleRequest;
import com.samat.money.model.SampleResponse;
import com.samat.money.repository.SampleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SampleServiceImpl extends AbstractService<SampleEntity, Short, SampleRepository,
        SampleElement, SampleResponse, SampleRequest, SampleMapper> implements SampleService {

    SampleMapper sampleMapper;
    SampleRepository sampleRepository;

    @Override
    SampleRepository getRepository() {
        return sampleRepository;
    }

    @Override
    SampleMapper getMapper() {
        return sampleMapper;
    }

    @Override
    public List<SampleElement> getList() {
        return super.getList();
    }
}
