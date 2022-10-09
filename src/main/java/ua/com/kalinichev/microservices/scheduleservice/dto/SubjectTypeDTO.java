package ua.com.kalinichev.microservices.scheduleservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubjectTypeDTO {

    @Getter
    @Setter
    private String group;

    @Getter
    @Setter
    private String type;

}
