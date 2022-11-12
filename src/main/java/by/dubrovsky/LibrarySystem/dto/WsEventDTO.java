package by.dubrovsky.LibrarySystem.dto;

import by.dubrovsky.LibrarySystem.models.Views;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@JsonView(Views.UserBook.class)
public class WsEventDTO {
    private ObjectType objectType;
    private EventType eventType;

    @JsonRawValue
    private String body;
}
