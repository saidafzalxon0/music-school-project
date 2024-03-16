package com.example.Musicschool.rest;

import com.example.Musicschool.dto.GeneralDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.dto.SchoolDto;
import com.example.Musicschool.service.GeneralService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/general")
public record GeneralRest(GeneralService service) {
    @PostMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<GeneralDto> post(@Valid @RequestBody GeneralDto dto){
        return service.post(dto);
    }
    @PatchMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<GeneralDto> patch(@Valid @RequestBody GeneralDto dto){
        return service.patch(dto);
    }
    @GetMapping
    public ResponseDto<List<GeneralDto>> get(){
        return service.get();
    }
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<GeneralDto> delete(@NotNull @PathVariable Long id){
        return service.delete(id);
    }
}
