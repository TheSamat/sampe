package com.samat.money.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseService<Index, Element, Response, Request> {
    List<Element> getList();

    Page<Element> getAll(int page, int size);

    Response getById(Index id);

    Response create(Request request);

    Response update(Index id, Request request);

    void delete(Index id);
}
