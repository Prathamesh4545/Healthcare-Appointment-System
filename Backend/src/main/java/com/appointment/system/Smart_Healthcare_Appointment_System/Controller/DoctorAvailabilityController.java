package com.appointment.system.Smart_Healthcare_Appointment_System.Controller;


import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.DoctorAvailabilityDto.DoctorAvailabilityRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.DoctorAvailabilityDto.DoctorAvailabilityResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Service.DoctorAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availability")
@RequiredArgsConstructor
public class DoctorAvailabilityController {

    private final DoctorAvailabilityService availabilityService;

    @PostMapping
    public ResponseEntity<DoctorAvailabilityResponseDto> create(@RequestBody DoctorAvailabilityRequestDto dto) {
        DoctorAvailabilityResponseDto response = availabilityService.create(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorAvailabilityResponseDto> update(
            @PathVariable Long id,
            @RequestBody DoctorAvailabilityRequestDto dto
    ) {
        DoctorAvailabilityResponseDto response = availabilityService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DoctorAvailabilityResponseDto> delete(@PathVariable Long id) {
        DoctorAvailabilityResponseDto response = availabilityService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<DoctorAvailabilityResponseDto>> getAvailabilityByDoctor(
            @PathVariable Long doctorId
    ) {
        List<DoctorAvailabilityResponseDto> response = availabilityService.getAllAvailableDoctorsById(doctorId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/specialization/{specId}")
    public ResponseEntity<List<DoctorAvailabilityResponseDto>> getAvailabilityBySpecialization(
            @PathVariable Long specId
    ) {
        List<DoctorAvailabilityResponseDto> response = availabilityService.getAvailableDoctorBySpecialization(specId);
        return ResponseEntity.ok(response);
    }
}
