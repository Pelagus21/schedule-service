package ua.com.kalinichev.microservices.scheduleservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.kalinichev.microservices.scheduleservice.apiCommunication.CoreWebClient;
import ua.com.kalinichev.microservices.scheduleservice.dto.LessonDTO;
import ua.com.kalinichev.microservices.scheduleservice.dto.SubjectDTO;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private CoreWebClient client;

    @Override
    public Iterable<LessonDTO> getSpecialtyLessons(Long id) {
        SubjectDTO[] allSubjs = getAllSubjects();
        Set<Long> lessonIds = new HashSet<>();
        Arrays.stream(allSubjs)
                .filter(subj -> Arrays.stream(subj.getSpecialties()).anyMatch(id::equals))
                .forEach(subj -> lessonIds.addAll(Arrays.asList(subj.getLessons())));
        return filterLessonsByMask(lessonIds);
    }

    @Override
    public Iterable<LessonDTO> getTeacherLessons(Long id) {
        SubjectDTO[] allSubjs = getAllSubjects();
        Set<Long> lessonIds = new HashSet<>();
        Arrays.stream(allSubjs)
                .filter(subj -> Arrays.stream(subj.getTeachers()).anyMatch(id::equals))
                .forEach(subj -> lessonIds.addAll(Arrays.asList(subj.getLessons())));
        return filterLessonsByMask(lessonIds);
    }

    @Override
    public Iterable<LessonDTO> getSubjectLessons(Long id) {
        SubjectDTO subj = this.client.fetchSubjectById(id);
        Long[] lessonIds = subj.getLessons();
        return filterLessonsByMask(Arrays.stream(lessonIds).collect(Collectors.toSet()));
    }

    @Override
    public Iterable<SubjectDTO> getTeacherSubjects(Long id) {
        SubjectDTO[] subjs = getAllSubjects();
        return Arrays.stream(subjs)
                .filter(subj -> Arrays.stream(subj.getTeachers()).anyMatch(id::equals))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<SubjectDTO> getSpecialtySubjects(Long id) {
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
