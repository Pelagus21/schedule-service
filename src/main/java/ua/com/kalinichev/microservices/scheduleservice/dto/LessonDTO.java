package ua.com.kalinichev.microservices.scheduleservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class LessonDTO {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String time;

    @Getter
    @Setter
    private Long subject;

    @Getter
    @Setter
    private Long teacher;

    @Getter
    @Setter
    private SubjectTypeDTO group;

    @Getter
    @Setter
    private String weeks;

    @Getter
    @Setter
    private RoomDTO room;

    @Getter
    @Setter
    private String dayOfWeek;
}
