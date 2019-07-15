package com.project.server.resourceServer.service;

import com.project.server.resourceServer.entity.Type;
import com.project.server.resourceServer.entity.dto.view.TypeDto;
import com.project.server.resourceServer.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("typeService")
@Transactional("dataTransactionManager")
public class TypeServiceImpl implements TypeService {

    private TypeRepository typeRepository;

    @Override
    public Iterable<TypeDto.LazyTypeDto> getAll() {
        return typeRepository.findAllBy();
    }

    @Override
    public Type save(Type type) {
        return typeRepository.save(type);
    }

    @Autowired
    public void setTypeRepository(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }
}
