package com.samat.money.controller;

import com.samat.money.model.SampleElement;
import com.samat.money.model.SampleRequest;
import com.samat.money.model.SampleResponse;
import com.samat.money.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
@RequiredArgsConstructor
public class SampleController extends BaseController<Short, SampleElement, SampleResponse, SampleRequest, SampleService> {
    private final SampleService sampleService;

    @Override
    protected SampleService getService() {
        return sampleService;
    }
}
