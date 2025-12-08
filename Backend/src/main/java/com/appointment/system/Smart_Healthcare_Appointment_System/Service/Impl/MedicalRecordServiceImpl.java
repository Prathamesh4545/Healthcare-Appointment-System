package com.appointment.system.Smart_Healthcare_Appointment_System.Service.Impl;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.MedicalRecordDto.MedicalRecordRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.MedicalRecordDto.MedicalRecordResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.PatientDto.PatientResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Exceptions.ResourceNotFoundException;
import com.appointment.system.Smart_Healthcare_Appointment_System.Mapper.MedicalRecordMapper;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Doctor;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.MedicalRecord;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Patient;
import com.appointment.system.Smart_Healthcare_Appointment_System.Repository.DoctorRepository;
import com.appointment.system.Smart_Healthcare_Appointment_System.Repository.MedicalRecordRepository;
import com.appointment.system.Smart_Healthcare_Appointment_System.Repository.PatientRepository;
import com.appointment.system.Smart_Healthcare_Appointment_System.Service.MedicalRecordService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final MedicalRecordMapper medicalRecordMapper;

    @Override
    public MedicalRecordResponseDto create(MedicalRecordRequestDto dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found by id: "+dto.getPatientId()));
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found by id: "+dto.getDoctorId()));
        MedicalRecord record = medicalRecordMapper.toEntity(dto);
        record.setPatient(patient);
        record.setDoctor(doctor);
        record.setPrescription(dto.getPrescription());
        record.setDiagnosis(dto.getDiagnosis());

        MedicalRecord saved = medicalRecordRepository.save(record);
        return medicalRecordMapper.toDto(saved);
    }

    @Override
    public MedicalRecordResponseDto update(Long id, MedicalRecordRequestDto dto) {
        MedicalRecord record = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical record not found"));
        record.setDiagnosis(dto.getDiagnosis());
        record.setPrescription(dto.getPrescription());

        return medicalRecordMapper.toDto(record);
    }

    @Override
    public List<MedicalRecordResponseDto> findByPatient(Long patientId) {
        return medicalRecordRepository.findByPatientId(patientId)
                .stream()
                .map(medicalRecordMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalRecordResponseDto delete(Long id) {
        MedicalRecord record = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical record not found"));
        medicalRecordRepository.delete(record);
        return medicalRecordMapper.toDto(record);
    }

    @Override
    public List<MedicalRecordResponseDto> getAll() {
        return medicalRecordRepository.findAll()
                .stream()
                .map(medicalRecordMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MedicalRecordResponseDto findRecordById(Long id) {
        MedicalRecord record = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical record not found"));
        return medicalRecordMapper.toDto(record);
    }
}
