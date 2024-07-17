package service_example.adapters.mappers;

import service_example.domain.Patient;
import service_example.adapters.dtos.PatientDto;

public class PatientMapper {
    public static PatientDto mapPatientToDto(Patient patient){
        return PatientDto.builder()
                .name(patient.getName())
                .surname(patient.getSurname())
                .dateOfBirth(patient.getDateOfBirth())
                .build();
    }
}
