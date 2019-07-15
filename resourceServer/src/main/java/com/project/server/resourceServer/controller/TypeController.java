package com.project.server.resourceServer.controller;

import com.project.server.resourceServer.entity.Type;
import com.project.server.resourceServer.entity.dto.view.TypeDto;
import com.project.server.resourceServer.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "types")
public class TypeController {

    private TypeService typeService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<TypeDto.LazyTypeDto> getTypes() {
        return typeService.getAll();
    }

    public Type createType(Type type) {
        return typeService.save(type);
    }

    @Autowired
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }
}
