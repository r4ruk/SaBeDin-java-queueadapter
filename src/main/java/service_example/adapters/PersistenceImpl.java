package service_example.adapters;

import service_example.adapters.dtos.PatientDto;
import service_example.adapters.mappers.PatientMapper;
import service_example.domain.Patient;
import service_example.use_case.IRepository;

import java.util.List;

public class PersistenceImpl implements IRepository {


    @Override
    public void create(Patient patient) {
        PatientDto dtopatient = PatientMapper.mapPatientToDto(patient);
    }

    @Override
    public List<Patient> getall() {

        return List.of();
    }
}
