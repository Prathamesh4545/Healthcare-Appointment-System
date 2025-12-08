package com.appointment.system.Smart_Healthcare_Appointment_System.Controller;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.MedicalRecordDto.MedicalRecordRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.MedicalRecordDto.MedicalRecordResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Mapper.MedicalRecordMapper;
import com.appointment.system.Smart_Healthcare_Appointment_System.Service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical_records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @PostMapping
    public ResponseEntity<MedicalRecordResponseDto> createMedicalRecord(@RequestBody MedicalRecordRequestDto dto){
        MedicalRecordResponseDto response = medicalRecordService.create(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecordResponseDto> updateMedicalRecord(@PathVariable Long id,@RequestBody MedicalRecordRequestDto dto){
        MedicalRecordResponseDto response = medicalRecordService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecordResponseDto>> getAllRecords(){
        return ResponseEntity.ok(medicalRecordService.getAll());
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<List<MedicalRecordResponseDto>> getRecordByPatient(@PathVariable Long id){
        return ResponseEntity.ok(medicalRecordService.findByPatient(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecordResponseDto> getRecordById(@PathVariable Long id){
        MedicalRecordResponseDto response = medicalRecordService.findRecordById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MedicalRecordResponseDto> deleteMedicalRecord(@PathVariable Long id){
        medicalRecordService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
