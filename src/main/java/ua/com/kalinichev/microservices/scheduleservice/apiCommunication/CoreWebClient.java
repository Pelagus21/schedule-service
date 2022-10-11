package ua.com.kalinichev.microservices.scheduleservice.apiCommunication;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import ua.com.kalinichev.microservices.scheduleservice.dto.LessonDTO;
import ua.com.kalinichev.microservices.scheduleservice.dto.SubjectDTO;

import java.util.NoSuchElementException;

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

    public SubjectDTO fetchSubjectById(Long id) throws WebClientResponseException {
        return this.client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("subjects/" + id)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(SubjectDTO.class)
                .block();
    }

    public String specialtyOrTeacherExistsById(Long id, boolean forSpecialty) {
      return this.client.get()
                .uri(uriBuilder ->
                        uriBuilder.path((forSpecialty ? "specialties/" : "teachers/") + id)
                .build())
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals,
                        response -> response.bodyToMono(String.class).map(NoSuchElementException::new))
                .bodyToMono(String.class)
                .block();
    }

}
