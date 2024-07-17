package service_example.use_case;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import service_example.domain.Patient;


import java.util.List;


public class PatientService implements IPatientService {
    IRepository repository;
    public PatientService(IRepository repository){
        this.repository = repository;
    }
    @Override
    public void create(String object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Patient patient = objectMapper.readValue(object, Patient.class);
            repository.create(patient);
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } {System.out.println("test");}
    }

    @Override
    public void delete(String object) {

    }

    @Override
    public List<String> getAll(String object) {
        return List.of();
    }

    @Override
    public List<String> getPatient(String object) {
        return List.of();
    }
}
