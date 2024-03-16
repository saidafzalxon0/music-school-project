package com.example.Musicschool.dto;

import com.example.Musicschool.status.AppStatusMessage;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NewDto {
    private Long id;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1,max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String title;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1,max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String about;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1,max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String shortly;
    @Size(max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String who_from;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    private Date date;
    @Size(min = 1,max = 255,message = AppStatusMessage.SIZE_MISMATCH)
    private String file;



}
