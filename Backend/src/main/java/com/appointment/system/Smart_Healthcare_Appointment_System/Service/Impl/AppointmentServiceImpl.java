package com.appointment.system.Smart_Healthcare_Appointment_System.Service.Impl;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.AppointmentDto.AppointmentRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.AppointmentDto.AppointmentResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Exceptions.ResourceNotFoundException;
import com.appointment.system.Smart_Healthcare_Appointment_System.Mapper.AppointmentMapper;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Appointment;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.AppointmentStatus;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Doctor;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Patient;
import com.appointment.system.Smart_Healthcare_Appointment_System.Repository.AppointmentRepository;
import com.appointment.system.Smart_Healthcare_Appointment_System.Repository.DoctorRepository;
import com.appointment.system.Smart_Healthcare_Appointment_System.Repository.PatientRepository;
import com.appointment.system.Smart_Healthcare_Appointment_System.Service.AppointmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Override
    public AppointmentResponseDto bookAppointment(AppointmentRequestDto dto) {
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found by id: "+dto.getDoctorId()));

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found by id: "+dto.getPatientId()));

        LocalDateTime time = LocalDateTime.parse(dto.getAppointmentTime());

        boolean isBooked = appointmentRepository.existsByDoctorAndAppointmentTime(doctor,time);

        if (isBooked) {
            throw new RuntimeException("Time slot already booked");
        }

        Appointment saved = appointmentMapper.toEntity(dto,doctor,patient);

        return appointmentMapper.toDto(saved);
    }

    @Override
    public AppointmentResponseDto updateStatus(Long id, String status) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        appointment.setStatus(AppointmentStatus.valueOf(status.toUpperCase()));
        return appointmentMapper.toDto(appointmentRepository.save(appointment));
    }

    @Override
    public List<AppointmentResponseDto> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId)
                .stream().map(appointmentMapper::toDto).toList();
    }

    @Override
    public List<AppointmentResponseDto> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId)
                .stream().map(appointmentMapper::toDto).toList();
    }

    @Override
    public void cancel(Long id) {
        updateStatus(id, "CANCELLED");
    }
}
