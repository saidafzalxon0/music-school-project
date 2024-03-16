package com.example.Musicschool.rest;

import com.example.Musicschool.dto.PositionDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.dto.SchoolDto;
import com.example.Musicschool.service.SchoolService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public record SchoolRest(SchoolService service) {
    @PostMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<SchoolDto> post(@Valid @RequestBody SchoolDto dto){
        return service.post(dto);
    }
    @PatchMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<SchoolDto> patch(@Valid @RequestBody SchoolDto dto){
        return service.patch(dto);
    }
    @GetMapping
    public ResponseDto<List<SchoolDto>> get(){
        return service.get();
    }
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<SchoolDto> delete(@NotNull @PathVariable Long id){
        return service.delete(id);
    }
}
