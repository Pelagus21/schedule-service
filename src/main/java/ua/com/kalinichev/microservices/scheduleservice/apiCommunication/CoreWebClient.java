package ua.com.kalinichev.microservices.scheduleservice.apiCommunication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ua.com.kalinichev.microservices.scheduleservice.dto.LessonDTO;
import ua.com.kalinichev.microservices.scheduleservice.dto.SubjectDTO;

@Component
public class CoreWebClient {

    @Autowired
    private WebClient client;

    public SubjectDTO[] fetchAllSubjects() {
        return this.client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("subjects")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(SubjectDTO[].class)
                .block();
    }

    public LessonDTO[] fetchAllLessons() {
        return this.client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("lessons")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(LessonDTO[].class)
                .block();
    }

    public SubjectDTO fetchSubjectById(Long id) {
        return this.client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("subjects/" + id)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(SubjectDTO.class)
                .block();
    }

}
