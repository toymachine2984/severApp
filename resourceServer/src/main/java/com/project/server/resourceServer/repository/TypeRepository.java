package com.project.server.resourceServer.repository;

import com.project.server.resourceServer.entity.Type;
import com.project.server.resourceServer.entity.dto.view.TypeDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends CrudRepository<Type, Long> {

    Iterable<TypeDto.LazyTypeDto> findAllBy();


}
