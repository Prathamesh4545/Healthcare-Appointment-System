package com.appointment.system.Smart_Healthcare_Appointment_System.Service.Impl;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.DoctorAvailabilityDto.DoctorAvailabilityRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.DoctorAvailabilityDto.DoctorAvailabilityResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Exceptions.ResourceNotFoundException;
import com.appointment.system.Smart_Healthcare_Appointment_System.Mapper.DoctorAvailabilityMapper;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Doctor;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.DoctorAvailability;
import com.appointment.system.Smart_Healthcare_Appointment_System.Repository.DoctorAvailabilityRepository;
import com.appointment.system.Smart_Healthcare_Appointment_System.Repository.DoctorRepository;
import com.appointment.system.Smart_Healthcare_Appointment_System.Service.DoctorAvailabilityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorAvailabilityServiceImpl implements DoctorAvailabilityService {

    private final DoctorAvailabilityRepository availabilityRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorAvailabilityMapper availabilityMapper;

    @Override
    public DoctorAvailabilityResponseDto create(DoctorAvailabilityRequestDto dto) {

        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        DoctorAvailability availability = availabilityMapper.toEntity(dto, doctor);

        DoctorAvailability saved = availabilityRepository.save(availability);
        return availabilityMapper.toDto(saved);
    }

    @Override
    public DoctorAvailabilityResponseDto update(Long id, DoctorAvailabilityRequestDto dto) {

        DoctorAvailability availability = availabilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Availability not found with ID: " + id));

        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + dto.getDoctorId()));

        availability.setDoctor(doctor);
        availability.setStartTime(dto.getStartTime());
        availability.setEndTime(dto.getEndTime());

        DoctorAvailability updated = availabilityRepository.save(availability);
        return availabilityMapper.toDto(updated);
    }

    @Override
    public List<DoctorAvailabilityResponseDto> getAllAvailableDoctorsById(Long doctorId) {
        List<DoctorAvailability> lst = availabilityRepository.findDoctorsById(doctorId);

        if(lst.isEmpty()){
            throw new ResourceNotFoundException("doctor not available!!");
        }

        return lst.stream()
                .map(availabilityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorAvailabilityResponseDto> getAvailableDoctorBySpecialization(Long specializationId) {
        List<DoctorAvailability> lst = availabilityRepository.findDoctorBySpecializationId(specializationId);

        if(lst.isEmpty()){
            throw new ResourceNotFoundException("doctor not available with specialization!!");
        }

        return lst.stream()
                .map(availabilityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorAvailabilityResponseDto delete(Long id) {
        DoctorAvailability availability = availabilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Availability not found with ID: " + id));

        DoctorAvailabilityResponseDto response = availabilityMapper.toDto(availability);

        availabilityRepository.delete(availability);

        return response;
    }

}
