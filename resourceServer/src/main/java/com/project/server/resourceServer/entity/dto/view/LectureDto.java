package com.project.server.resourceServer.entity.dto.view;

import com.project.server.resourceServer.entity.dto.ILecture;

public class LectureDto {

    public interface LazyLectureDto extends ILecture {

        public Long getId();

        public String getName();

        public String getPosition();

        public String getDescription();

        public String getPhotoLink();
    }
}
