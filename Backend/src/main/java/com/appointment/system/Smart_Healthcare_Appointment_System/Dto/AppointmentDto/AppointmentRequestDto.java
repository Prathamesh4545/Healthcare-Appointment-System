package com.appointment.system.Smart_Healthcare_Appointment_System.Dto.AppointmentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestDto {
    private Long patientId;
    private Long doctorId;
    private String  appointmentTime;
    private String remarks;
}
