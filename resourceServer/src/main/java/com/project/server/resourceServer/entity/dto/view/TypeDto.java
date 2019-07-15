package com.project.server.resourceServer.entity.dto.view;

import com.project.server.resourceServer.entity.dto.IType;

public class TypeDto {

    public interface LazyTypeDto extends IType {

        public Long getId();

        public String getValue();

        public String getDescription();
    }

}
