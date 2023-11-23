package com.samat.money.controller;

import com.samat.money.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public abstract class BaseController<Index, Element, Response, Request,
        Service extends BaseService<Index, Element, Response, Request>> {

    abstract Service getService();

    @GetMapping("/list")
    public List<Element> getList() {
        return getService().getList();
    }

    @GetMapping("/")
    public Page<Element> getAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "25") int size) {
        return getService().getAll(page, size);
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable Index id) {
        return getService().getById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Response> create(@Valid @RequestBody Request request) {
        return new ResponseEntity<>(getService().create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> create(@PathVariable Index id, @Valid @RequestBody Request request) {
        return new ResponseEntity<>(getService().update(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Index id) {
        getService().delete(id);
    }
}
