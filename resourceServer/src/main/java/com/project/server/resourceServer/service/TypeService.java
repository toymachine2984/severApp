package com.project.server.resourceServer.service;

import com.project.server.resourceServer.entity.Type;
import com.project.server.resourceServer.entity.dto.view.TypeDto;

public interface TypeService {

    Iterable<TypeDto.LazyTypeDto> getAll();

    Type save(Type type);


}
