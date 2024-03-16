package com.example.Musicschool.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {
    private String status;
    private T data;
}
