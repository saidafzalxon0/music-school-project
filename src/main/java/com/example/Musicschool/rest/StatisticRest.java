package com.example.Musicschool.rest;

import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.dto.SchoolDto;
import com.example.Musicschool.dto.StatisticDto;
import com.example.Musicschool.service.StatisticService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/statistic")
public record StatisticRest(StatisticService service) {
    @PostMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<StatisticDto> post(@Valid @RequestBody StatisticDto dto){
        return service.post(dto);
    }
    @PatchMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<StatisticDto> patch(@Valid @RequestBody StatisticDto dto){
        return service.patch(dto);
    }
    @GetMapping
    public ResponseDto<List<StatisticDto>> get(){
        return service.get();
    }
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<StatisticDto> delete(@NotNull @PathVariable Long id){
        return service.delete(id);
    }
}
