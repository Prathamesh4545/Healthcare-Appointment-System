package com.appointment.system.Smart_Healthcare_Appointment_System.Service;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.AppointmentDto.AppointmentRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.AppointmentDto.AppointmentResponseDto;

import java.util.List;

public interface AppointmentService {
    AppointmentResponseDto bookAppointment(AppointmentRequestDto dto);
    AppointmentResponseDto updateStatus(Long id, String status);
    List<AppointmentResponseDto> getAppointmentsByDoctor(Long doctorId);
    List<AppointmentResponseDto> getAppointmentsByPatient(Long patientId);
    void cancel(Long id);
}
