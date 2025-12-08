package com.appointment.system.Smart_Healthcare_Appointment_System.Service;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.DoctorAvailabilityDto.DoctorAvailabilityRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.DoctorAvailabilityDto.DoctorAvailabilityResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.DoctorAvailability;

import java.util.List;

public interface DoctorAvailabilityService {
    DoctorAvailabilityResponseDto create(DoctorAvailabilityRequestDto dto);
    DoctorAvailabilityResponseDto update(Long id,DoctorAvailabilityRequestDto dto);
    List<DoctorAvailabilityResponseDto> getAllAvailableDoctorsById(Long doctorId);
    List<DoctorAvailabilityResponseDto> getAvailableDoctorBySpecialization(Long specializationId);
    DoctorAvailabilityResponseDto delete(Long id);
}
