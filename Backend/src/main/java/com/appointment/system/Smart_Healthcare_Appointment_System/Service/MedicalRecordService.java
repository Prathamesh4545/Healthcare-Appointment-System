package com.appointment.system.Smart_Healthcare_Appointment_System.Service;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.MedicalRecordDto.MedicalRecordRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.MedicalRecordDto.MedicalRecordResponseDto;

import java.util.List;

public interface MedicalRecordService {
    MedicalRecordResponseDto create(MedicalRecordRequestDto dto);
    MedicalRecordResponseDto update(Long id,MedicalRecordRequestDto dto);
    List<MedicalRecordResponseDto> findByPatient(Long patientId);
    MedicalRecordResponseDto delete(Long id);
    List<MedicalRecordResponseDto> getAll();
    MedicalRecordResponseDto findRecordById(Long id);
}
