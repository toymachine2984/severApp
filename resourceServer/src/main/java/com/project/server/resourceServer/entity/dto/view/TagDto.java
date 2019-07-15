package com.project.server.resourceServer.entity.dto.view;

import com.project.server.resourceServer.entity.dto.ITag;

public class TagDto {

    public interface LazyTagDto extends ITag {

        public Long getId();

        public String getValue();

        public String getDescription();

    }
}
