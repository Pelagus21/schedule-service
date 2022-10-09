package ua.com.kalinichev.microservices.scheduleservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.kalinichev.microservices.scheduleservice.dto.LessonDTO;
import ua.com.kalinichev.microservices.scheduleservice.dto.SubjectDTO;
import ua.com.kalinichev.microservices.scheduleservice.services.ScheduleService;


@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService service;

    @GetMapping("/specialty/{id}")
    public Iterable<LessonDTO> getSpecialtySchedule(@PathVariable(value = "id") Long id) {
        return this.service.getSpecialtyLessons(id);
    }

    @GetMapping("/teacher/{id}")
    public Iterable<LessonDTO> getTeacherSchedule(@PathVariable(value = "id") Long id) {
        return this.service.getTeacherLessons(id);
    }

    @GetMapping("/subject/{id}")
    public Iterable<LessonDTO> getSubjectSchedule(@PathVariable(value = "id") Long id) {
        return this.service.getSubjectLessons(id);
    }

    @GetMapping("/teacherSubjects/{id}")
    public Iterable<SubjectDTO> getTeacherSubjects(@PathVariable(value = "id") Long id) {
        return this.service.getTeacherSubjects(id);
    }
    @GetMapping("/specialtySubjects/{id}")
    public Iterable<SubjectDTO> getSpecialtySubjects(@PathVariable(value = "id") Long id) {
        return this.service.getSpecialtySubjects(id);
    }

}
