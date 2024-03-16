package com.example.Musicschool.rest;

import com.example.Musicschool.dto.NewDto;
import com.example.Musicschool.dto.ResponseDto;
import com.example.Musicschool.entity.New;
import com.example.Musicschool.exception.DatabaseException;
import com.example.Musicschool.service.NewService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
@RestController
@RequestMapping("/news")
public record NewRest(NewService service) {


    @PostMapping(consumes = "multipart/form-data")
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<NewDto> post(@RequestPart String title,@RequestPart String about,@RequestPart String shortly,@RequestPart(required = false) String who_from,@RequestPart String date,@RequestPart(required = false) MultipartFile file){
        try {
            return service.post(title, about, shortly, who_from, new SimpleDateFormat("dd-MM-yyyy").parse(date) , file);
        } catch (ParseException e) {
            throw new DatabaseException("Date time not matches");
        }
    }
    @PatchMapping(consumes = "multipart/form-data")
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<NewDto> patch(@RequestPart String id,@RequestPart String title,@RequestPart String about,@RequestPart String shortly,@RequestPart(required = false) String who_from,@RequestPart String date,@RequestPart(required = false) MultipartFile file){
        try {
            return service.patch(Long.parseLong(id), title, about, shortly, who_from, new SimpleDateFormat("dd-MM-yyy").parse(date), file);
        } catch (ParseException e) {
            throw new DatabaseException("Date time not matches");
        }
    }
    @GetMapping("/{id}")
    public  ResponseDto<NewDto> getById(@NotNull @PathVariable Long id){
        return service.getById(id);
    }
    @GetMapping("/byDate")
    public ResponseDto<Page<New>> getByDate(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1") int size,
            @RequestParam(defaultValue = "desc") String direction,
            @RequestParam String date) {

        try {
            Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "id"));
            return service.getByDate(pageable, new SimpleDateFormat("dd-MM-yyyy").parse(date));
        } catch (ParseException e) {
            throw new DatabaseException("Date time not matches");
        }
    }

    @GetMapping
    public  ResponseDto<Page<New>> getAll(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "1") int size,
                                          @RequestParam(defaultValue = "desc") String direction){
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "id"));
        return service.getAll(pageable);
    }
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    public ResponseDto<NewDto> delete(@PathVariable Long id){
        return service.delete(id);
    }
}
