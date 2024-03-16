package com.example.Musicschool.rest;

import com.example.Musicschool.dto.DirectionDto;
import com.example.Musicschool.dto.PartnerDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.service.PartnerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/partner")
public record PartnerRest(PartnerService service) {
    @PostMapping(consumes = "multipart/form-data")
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<PartnerDto> post(@RequestParam(name = "name") @NotNull String name,@RequestParam(name = "photo") @NotNull MultipartFile photo, @RequestParam(name = "linkWebsite") @NotNull String linkWebsite){
        return service.post(name,photo,linkWebsite);
    }

    @PatchMapping(consumes = "multipart/form-data")
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<PartnerDto> patch(@RequestParam(name = "id") @NotNull Long id, @RequestParam(name = "name") @NotNull String name,@RequestParam(name = "photo",required = false) @NotNull MultipartFile photo, @RequestParam(name = "linkWebsite") @NotNull String linkWebsite){
        return service.patch(id,name,photo,linkWebsite);
    }

    @GetMapping("/{id}")
    public ResponseDto<PartnerDto> get(@NotNull @PathVariable Long id){
        return service.get(id);
    }
    @GetMapping("/getAll")
    public ResponseDto<List<PartnerDto>> getAll(){
        return service.getAll();
    }


    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<PartnerDto> delete(@NotNull @PathVariable Long id){
        return service.delete(id);
    }
}

