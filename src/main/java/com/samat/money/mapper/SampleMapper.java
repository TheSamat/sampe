package com.samat.money.mapper;

import com.samat.money.entity.SampleEntity;
import com.samat.money.model.SampleElement;
import com.samat.money.model.SampleRequest;
import com.samat.money.model.SampleResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SampleMapper extends DefaultMapper<SampleEntity, SampleElement, SampleResponse, SampleRequest> {
}
