package service_example.use_case;

import service_example.domain.Patient;

import java.util.List;

public interface IRepository {
    void create(Patient patient);
    List<Patient> getall();
}
