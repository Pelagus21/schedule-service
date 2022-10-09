package ua.com.kalinichev.microservices.scheduleservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomDTO {

    @Getter
    @Setter
    private String room;

    @Getter
    @Setter
    private String type;

}
