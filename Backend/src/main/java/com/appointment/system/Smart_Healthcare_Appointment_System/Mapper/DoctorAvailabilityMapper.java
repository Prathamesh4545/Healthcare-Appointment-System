package com.appointment.system.Smart_Healthcare_Appointment_System.Mapper;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.DoctorAvailabilityDto.DoctorAvailabilityRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.DoctorAvailabilityDto.DoctorAvailabilityResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Doctor;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.DoctorAvailability;
import org.springframework.stereotype.Component;

@Component
public class DoctorAvailabilityMapper {

    // ==============================
    // Entity → Response DTO
    // ==============================
    public DoctorAvailabilityResponseDto toDto(DoctorAvailability availability) {

        DoctorAvailabilityResponseDto dto = new DoctorAvailabilityResponseDto();

        dto.setId(availability.getId());
        dto.setDoctorId(availability.getDoctor().getId());
        dto.setDoctorName(availability.getDoctor().getFullName());

//        if (availability.getDoctor().getSpecialization() != null) {
//            dto.setSpecializationName(availability.getDoctor().getSpecialization().getName());
//        }

        dto.setStartTime(availability.getStartTime().toString());
        dto.setEndTime(availability.getEndTime().toString());
        dto.setAvailable(availability.isAvailable());

        return dto;
    }

    // ==============================
    // Request DTO → Entity
    // ==============================
    public DoctorAvailability toEntity(DoctorAvailabilityRequestDto dto, Doctor doctor) {

        DoctorAvailability availability = new DoctorAvailability();

        availability.setDoctor(doctor);
        availability.setStartTime(dto.getStartTime());
        availability.setEndTime(dto.getEndTime());
        availability.setAvailable(true);

        return availability;
    }
}
