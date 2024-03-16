package com.example.Musicschool.dto;

import com.example.Musicschool.entity.Department;
import com.example.Musicschool.status.AppStatusMessage;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DirectionDto {
    private Long id;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1,max = 255,message = AppStatusMessage.SIZE_MISMATCH)
    private String name;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1,max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String history;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private Department department;
    private Integer level;
}
