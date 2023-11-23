package com.samat.money.Data.mapper.base;

import com.samat.money.Data.entity.SampleEntity;
import com.samat.money.Data.mapper.BaseMapper;
import com.samat.money.Domain.model.SampleElement;
import com.samat.money.Domain.model.SampleRequest;
import com.samat.money.Domain.model.SampleResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SampleMapper extends BaseMapper<SampleEntity, SampleElement, SampleResponse, SampleRequest> {
}
