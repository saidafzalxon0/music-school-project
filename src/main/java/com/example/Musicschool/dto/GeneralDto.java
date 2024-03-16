package com.example.Musicschool.dto;

import com.example.Musicschool.status.AppStatusMessage;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralDto {

    private Long id;
    @Size(max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String phone;
    @Size(max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String fax;
    @Size(max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String email;
    @Size(max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String location;
    @Size(max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String target;
    @Size(max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String directions;
    @Size(max = 100000,message = AppStatusMessage.SIZE_MISMATCH)
    private String additional;
}
