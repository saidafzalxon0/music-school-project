package com.example.Musicschool.dto;

import com.example.Musicschool.entity.Direction;
import com.example.Musicschool.entity.Position;
import com.example.Musicschool.status.AppStatusMessage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployeeDto {
    private Long id;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1, max = 255, message = AppStatusMessage.SIZE_MISMATCH)
    private String fullName;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1, max = 10000, message = AppStatusMessage.SIZE_MISMATCH)
    private String about;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1, max = 10000, message = AppStatusMessage.SIZE_MISMATCH)
    private String editor;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private Position position;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private Direction direction;

    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @Min(value = 1, message = AppStatusMessage.NEGATIVE_VALUE)
    @Max(value = 10, message = AppStatusMessage.NEGATIVE_VALUE)
    private Integer type;
    private String photoLink;
    private List<String> listFile;

}
