package ua.com.kalinichev.microservices.scheduleservice.services;

import ua.com.kalinichev.microservices.scheduleservice.dto.LessonDTO;
import ua.com.kalinichev.microservices.scheduleservice.dto.SubjectDTO;


public interface ScheduleService {

    Iterable<LessonDTO> getSpecialtyLessons(Long id);

    Iterable<LessonDTO> getTeacherLessons(Long id);

    Iterable<LessonDTO> getSubjectLessons(Long id);

    Iterable<SubjectDTO> getTeacherSubjects(Long id);

    Iterable<SubjectDTO> getSpecialtySubjects(Long id);
}
