package com.example.Musicschool.dto;

import com.example.Musicschool.status.AppStatusMessage;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatisticDto {
    private Long id;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1,max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String title1;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1,max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String number1;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1,max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String title2;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1,max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String number2;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1,max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String title3;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1,max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String number3;
}
