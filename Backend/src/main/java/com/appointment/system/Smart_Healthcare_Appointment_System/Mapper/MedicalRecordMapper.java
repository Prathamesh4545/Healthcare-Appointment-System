package com.appointment.system.Smart_Healthcare_Appointment_System.Mapper;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.MedicalRecordDto.MedicalRecordRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.MedicalRecordDto.MedicalRecordResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.MedicalRecord;
import org.springframework.stereotype.Component;

@Component
public class MedicalRecordMapper {
    public MedicalRecord toEntity(MedicalRecordRequestDto dto){
        MedicalRecord record = new MedicalRecord();
        record.setDiagnosis(dto.getDiagnosis());
        record.setPrescription(dto.getPrescription());
        record.setPrescription(dto.getPrescription());
        return record;
    }

    public MedicalRecordResponseDto toDto(MedicalRecord record){
        MedicalRecordResponseDto dto = new MedicalRecordResponseDto();
        dto.setId(record.getId());
        dto.setDiagnosis(record.getDiagnosis());
        dto.setPrescription(record.getPrescription());
        dto.setPrescription(record.getPrescription());
        dto.setCreatedAt(record.getCreateAt());
        dto.setDoctorName(record.getDoctor().getFullName());
        dto.setPatientName(record.getPatient().getFullName());
        return dto;
    }
}
