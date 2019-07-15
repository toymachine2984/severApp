package com.project.server.resourceServer.service;

import com.project.server.resourceServer.entity.Tag;
import com.project.server.resourceServer.entity.dto.view.TagDto;

import java.util.List;

public interface TagsService {

    Iterable<TagDto.LazyTagDto> getAll();

    Iterable<Tag> saveAll(List<Tag> tags);

    Tag save(Tag Tag);


}
