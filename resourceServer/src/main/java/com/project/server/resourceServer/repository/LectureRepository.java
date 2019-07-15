package com.project.server.resourceServer.repository;

import com.project.server.resourceServer.entity.Lecture;
import com.project.server.resourceServer.entity.dto.view.LectureDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends CrudRepository<Lecture, Long> {

    Iterable<LectureDto.LazyLectureDto> findAllBy();
}
