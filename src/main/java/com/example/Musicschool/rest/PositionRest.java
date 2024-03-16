package com.example.Musicschool.rest;

import com.example.Musicschool.dto.PositionDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.service.PositionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/position")
public record PositionRest(PositionService service) {
    @PostMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<PositionDto> post(@Valid @RequestBody PositionDto dto){
        return service.post(dto);
    }
    @PatchMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<PositionDto> patch(@Valid @RequestBody PositionDto dto){
        return service.patch(dto);
    }
    @GetMapping("/{id}")
    public ResponseDto<PositionDto> get(@NotNull @PathVariable Long id){
        return service.get(id);
    }
    @GetMapping("/getAll")
    public ResponseDto<List<PositionDto>> getAll(){
        return service.getAll();
    }
    @SecurityRequirement(name = "Authorization")
    @DeleteMapping("/{id}")
    public ResponseDto<PositionDto> delete(@NotNull @PathVariable Long id){
        return service.delete(id);
    }
}