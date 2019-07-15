package com.project.server.resourceServer.repository;

import com.project.server.resourceServer.entity.Tag;
import com.project.server.resourceServer.entity.dto.view.TagDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends CrudRepository<Tag, Long> {

    Iterable<TagDto.LazyTagDto> findAllBy();
}
