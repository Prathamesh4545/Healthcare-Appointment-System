package com.appointment.system.Smart_Healthcare_Appointment_System.Repository;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.DoctorAvailabilityDto.DoctorAvailabilityResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorAvailabilityRepository extends JpaRepository<DoctorAvailability, Long> {

    List<DoctorAvailability> findDoctorsById(Long doctorId);

    List<DoctorAvailability> findDoctorBySpecializationId(Long specializationId);
}
