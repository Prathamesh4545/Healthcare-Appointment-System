package com.appointment.system.Smart_Healthcare_Appointment_System.Dto.DoctorAvailabilityDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAvailabilityResponseDto {
    private Long id;
    private Long doctorId;
    private String doctorName;
    private String specializationName;
    private String startTime;
    private String endTime;
    private boolean available;
}
