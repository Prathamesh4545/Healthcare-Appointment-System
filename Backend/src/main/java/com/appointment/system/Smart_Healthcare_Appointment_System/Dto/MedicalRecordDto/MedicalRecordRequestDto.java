package com.appointment.system.Smart_Healthcare_Appointment_System.Dto.MedicalRecordDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordRequestDto {
    private Long patientId;
    private Long doctorId;
    private String prescription;
    private String diagnosis;
}
