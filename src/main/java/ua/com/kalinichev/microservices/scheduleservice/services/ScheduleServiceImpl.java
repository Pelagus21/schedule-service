package ua.com.kalinichev.microservices.scheduleservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import ua.com.kalinichev.microservices.scheduleservice.apiCommunication.CoreWebClient;
import ua.com.kalinichev.microservices.scheduleservice.dto.LessonDTO;
import ua.com.kalinichev.microservices.scheduleservice.dto.SubjectDTO;
import ua.com.kalinichev.microservices.scheduleservice.exceptions.BadRequestException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private CoreWebClient client;

    @Override
    public Iterable<LessonDTO> getSpecialtyLessons(Long id) {
        this.client.specialtyOrTeacherExistsById(id, true);
        SubjectDTO[] allSubjs = getAllSubjects();
        Set<Long> lessonIds = new HashSet<>();
        Arrays.stream(allSubjs)
                .filter(subj -> Arrays.stream(subj.getSpecialties()).anyMatch(id::equals))
                .forEach(subj -> lessonIds.addAll(Arrays.asList(subj.getLessons())));
        return filterLessonsByMask(lessonIds);
    }

    @Override
    public Iterable<LessonDTO> getTeacherLessons(Long id) {
        this.client.specialtyOrTeacherExistsById(id, false);
        SubjectDTO[] allSubjs = getAllSubjects();
        Set<Long> lessonIds = new HashSet<>();
        Arrays.stream(allSubjs)
                .filter(subj -> Arrays.stream(subj.getTeachers()).anyMatch(id::equals))
                .forEach(subj -> lessonIds.addAll(Arrays.asList(subj.getLessons())));
        return filterLessonsByMask(lessonIds);
    }

    @Override
    public Iterable<LessonDTO> getSubjectLessons(Long id) {
        try {
            SubjectDTO subj = this.client.fetchSubjectById(id);
            Long[] lessonIds = subj.getLessons();
            return filterLessonsByMask(Arrays.stream(lessonIds).collect(Collectors.toSet()));
        } catch(WebClientResponseException ex) {
            if(ex.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NoSuchElementException("Element not found");
            else
                throw new BadRequestException("Bad request");
        }
    }

    @Override
    public Iterable<SubjectDTO> getTeacherSubjects(Long id) {
        this.client.specialtyOrTeacherExistsById(id, false);
        SubjectDTO[] subjs = getAllSubjects();
        return Arrays.stream(subjs)
                .filter(subj -> Arrays.stream(subj.getTeachers()).anyMatch(id::equals))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<SubjectDTO> getSpecialtySubjects(Long id) {
        this.client.specialtyOrTeacherExistsById(id, true);
        SubjectDTO[] subjs = getAllSubjects();
        return Arrays.stream(subjs)
                .filter(subj -> Arrays.stream(subj.getSpecialties()).anyMatch(id::equals))
                .collect(Collectors.toList());
    }

    //private methods

    private SubjectDTO[] getAllSubjects() {
        return this.client.fetchAllSubjects();
    }

    private LessonDTO[] getAllLessons() {
        return this.client.fetchAllLessons();
    }

    private Iterable<LessonDTO> filterLessonsByMask(Set<Long> idsMask) {
        LessonDTO[] allLessons = getAllLessons();
        return Arrays.stream(allLessons).filter(less -> idsMask.contains(less.getId())).collect(Collectors.toList());
    }
}
