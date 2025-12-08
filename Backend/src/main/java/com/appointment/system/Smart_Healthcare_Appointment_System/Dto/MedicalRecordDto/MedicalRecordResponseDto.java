package com.appointment.system.Smart_Healthcare_Appointment_System.Dto.MedicalRecordDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordResponseDto {
    private Long id;
    private String patientName;
    private String doctorName;
    private LocalDateTime createdAt;
    private String prescription;
    private String diagnosis;
}
