package com.appointment.system.Smart_Healthcare_Appointment_System.Mapper;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.AppointmentDto.AppointmentRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.AppointmentDto.AppointmentResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Appointment;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Doctor;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Patient;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AppointmentMapper {

    // ==============================
    // Request DTO → Entity
    // ==============================
    public Appointment toEntity(AppointmentRequestDto dto, Doctor doctor, Patient patient){
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentTime(LocalDateTime.parse(dto.getAppointmentTime()));
        appointment.setRemarks(dto.getRemarks());
        return appointment;
    }

    // ==============================
    // Entity → Response DTO
    // ==============================
    public AppointmentResponseDto toDto(Appointment appointment){
        AppointmentResponseDto dto = new AppointmentResponseDto();
        dto.setAppointmentId(appointment.getId());
        dto.setDoctorId(appointment.getDoctor().getId());
        dto.setPatientId(appointment.getPatient().getId());
        dto.setDoctorName(appointment.getDoctor().getFullName());
        dto.setPatientName(appointment.getPatient().getFullName());
        dto.setAppointmentTime(appointment.getAppointmentTime());
        dto.setStatus(appointment.getStatus());
        dto.setRemark(appointment.getRemarks());
        return dto;
    }

}
