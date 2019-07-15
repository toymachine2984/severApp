package com.project.server.resourceServer.service;

import com.project.server.resourceServer.entity.Lecture;
import com.project.server.resourceServer.entity.dto.view.LectureDto;


public interface LectureService {

    Iterable<LectureDto.LazyLectureDto> getAll();

    Lecture save(Lecture type);
}
