package com.project.server.resourceServer.service;

import com.project.server.resourceServer.entity.Tag;
import com.project.server.resourceServer.entity.dto.view.TagDto;
import com.project.server.resourceServer.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("tagsService")
@Transactional("dataTransactionManager")
public class TagsServiceImpl implements TagsService {

    private TagsRepository tagsRepository;

    @Override
    public Iterable<TagDto.LazyTagDto> getAll() {
        return tagsRepository.findAllBy();
    }

    @Override
    public Iterable<Tag> saveAll(List<Tag> tags) {
        return tagsRepository.saveAll(tags);
    }

    @Override
    public Tag save(Tag tag) {
        return tagsRepository.save(tag);
    }

    @Autowired
    public void setTagsRepository(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }
}
