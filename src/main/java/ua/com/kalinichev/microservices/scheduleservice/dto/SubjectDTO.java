package ua.com.kalinichev.microservices.scheduleservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubjectDTO {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int quantOfGroups;

    @Getter
    @Setter
    private Long[] teachers;

    @Getter
    @Setter
    private Long[] specialties;

    @Getter
    @Setter
    private Long[] lessons;

}
