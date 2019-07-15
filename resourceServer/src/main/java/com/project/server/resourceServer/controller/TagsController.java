package com.project.server.resourceServer.controller;

import com.project.server.resourceServer.entity.Tag;
import com.project.server.resourceServer.entity.dto.view.TagDto;
import com.project.server.resourceServer.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "tags")
public class TagsController {

    private TagsService tagsService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<TagDto.LazyTagDto> getTags() {
        return tagsService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Tag addTag(@RequestBody Tag tag) {
        return tagsService.save(tag);
    }


    @Autowired
    public void setTagsService(TagsService tagsService) {
        this.tagsService = tagsService;
    }
}
