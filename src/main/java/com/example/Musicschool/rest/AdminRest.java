package com.example.Musicschool.rest;

import com.example.Musicschool.dto.AdminDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.service.AdminService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public record AdminRest(AdminService service) {
    @PostMapping("/sign-in")
    private ResponseDto<String> signIn(@Valid @RequestBody AdminDto adminDto){
        return service.signIn(adminDto);
    }
    @PostMapping("/sign-up")
    @SecurityRequirement(name = "Authorization")
    private ResponseDto<AdminDto> signUp(@Valid @RequestBody AdminDto adminDto){
        return service.signUp(adminDto);
    }

    @PatchMapping("/edit")
    @SecurityRequirement(name = "Authorization")
    private ResponseDto<String> update(@Valid @RequestBody AdminDto adminDto){
        return service.patch(adminDto);
    }
    @GetMapping("/get")
    @SecurityRequirement(name = "Authorization")
    private ResponseDto<List<AdminDto>> getAll(){
        return service.getAllAdmin();
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    private ResponseDto<AdminDto> delete(@NotNull @PathVariable Long id){
        return service.delete(id);
    }
}
