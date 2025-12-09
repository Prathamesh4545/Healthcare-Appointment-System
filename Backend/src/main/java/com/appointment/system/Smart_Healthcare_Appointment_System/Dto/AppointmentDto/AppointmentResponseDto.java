package com.appointment.system.Smart_Healthcare_Appointment_System.Dto.AppointmentDto;

import com.appointment.system.Smart_Healthcare_Appointment_System.Model.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseDto {
    private Long appointmentId;
    private Long doctorId;
    private Long patientId;
    private String patientName;
    private String doctorName;
    private LocalDateTime appointmentTime;
    private AppointmentStatus status;
    private String remark;
}
