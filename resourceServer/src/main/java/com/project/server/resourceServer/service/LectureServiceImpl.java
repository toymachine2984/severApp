package com.project.server.resourceServer.service;

import com.project.server.resourceServer.entity.Lecture;
import com.project.server.resourceServer.entity.dto.view.LectureDto;
import com.project.server.resourceServer.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("lectureService")
@Transactional("dataTransactionManager")
public class LectureServiceImpl implements LectureService {

    private LectureRepository lectureRepository;

    @Override
    public Iterable<LectureDto.LazyLectureDto> getAll() {
        return lectureRepository.findAllBy();
    }

    @Override
    public Lecture save(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    @Autowired
    public void setLectureRepository(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }
}
