package com.project.server.resourceServer.controller;

import com.project.server.resourceServer.entity.Lecture;
import com.project.server.resourceServer.entity.dto.view.LectureDto;
import com.project.server.resourceServer.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "lectures")
public class LectureController {

    private LectureService lectureService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<LectureDto.LazyLectureDto> getLectures() {
        return lectureService.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Lecture addLecture(@RequestBody Lecture lecture) {
        return lectureService.save(lecture);
    }

    @Autowired
    public void setLectureService(LectureService lectureService) {
        this.lectureService = lectureService;
    }
}
