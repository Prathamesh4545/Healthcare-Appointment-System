package com.appointment.system.Smart_Healthcare_Appointment_System.Repository;

import com.appointment.system.Smart_Healthcare_Appointment_System.Model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByPatientId(Long patientId);
}
