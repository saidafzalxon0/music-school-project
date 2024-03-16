package com.example.Musicschool.dto;

import com.example.Musicschool.status.AppStatusMessage;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AcceptanceDto {
    private Long id;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1,max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String string1;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1,max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String string2;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1,max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String string3;
    @NotNull(message = AppStatusMessage.NULL_VALUE)
    @NotEmpty(message = AppStatusMessage.EMPTY_STRING)
    @Size(min = 1,max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String string4;

}
