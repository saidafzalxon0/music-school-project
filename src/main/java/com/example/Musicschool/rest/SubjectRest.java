package com.example.Musicschool.rest;

import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.dto.SubjectDto;
import com.example.Musicschool.projection.SubjectDirectionGetProjection;
import com.example.Musicschool.projection.SubjectGetProjection;
import com.example.Musicschool.service.SubjectService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public record SubjectRest(SubjectService service) {
    @PostMapping
    @SecurityRequirement(name = "Authorization")
   public ResponseDto<SubjectDirectionGetProjection> post(@Valid @RequestBody SubjectDto subjectDto){
       return service.post(subjectDto);
   }
    @PatchMapping
    @SecurityRequirement(name = "Authorization")
   public ResponseDto<SubjectDirectionGetProjection> patch(@Valid @RequestBody SubjectDto subjectDto){
       return service.patch(subjectDto);
   }
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
   public ResponseDto<SubjectDirectionGetProjection> delete(@NotNull @PathVariable Long id){
       return service.delete(id);
   }
    @GetMapping("direction/{id}")
   public ResponseDto<List<SubjectGetProjection>> getAllByDirectionId(@NotNull @PathVariable Long id){
       return service.getAllByDirectionId(id);
    }
}
