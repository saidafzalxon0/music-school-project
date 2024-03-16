package com.example.Musicschool.rest;

import com.example.Musicschool.dto.DirectionDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.service.DirectionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direction")
public record DirectionRest(DirectionService service) {
    @PostMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<DirectionDto> post(@Valid @RequestBody DirectionDto dto){
     return service.post(dto);
   }
    @PatchMapping
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<DirectionDto> patch(@Valid @RequestBody DirectionDto dto){
       return service.patch(dto);
    }
    @GetMapping("/{id}")
    public ResponseDto<DirectionDto> get(@NotNull @PathVariable Long id){
       return service.get(id);
    }

    @GetMapping("/department/{id}")
    public ResponseDto<List<DirectionDto>> getDirection(@NotNull @PathVariable Long id){
        return service.getByDepartment(id);
    }


    @GetMapping("/getAll")
    public ResponseDto<List<DirectionDto>> getAll(){
       return service.getAll();
    }


    @DeleteMapping("/{id}")
    public ResponseDto<DirectionDto> delete(@NotNull @PathVariable Long id){
       return service.delete(id);
    }
}
