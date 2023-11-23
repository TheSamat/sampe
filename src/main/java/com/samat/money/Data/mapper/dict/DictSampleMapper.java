package com.samat.money.Data.mapper.dict;

import com.samat.money.Data.entity.SampleEntity;
import com.samat.money.Web.dict.DictMapper;
import com.samat.money.Domain.model.SampleRequest;
import com.samat.money.Domain.model.SampleResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DictSampleMapper extends DictMapper<SampleEntity, SampleResponse, SampleRequest> {
}
