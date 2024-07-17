package service_example.use_case;

import java.util.List;

public interface IPatientService {
    void create(String object);
    void delete(String object);
    List<String> getAll(String object);
    List<String> getPatient(String object);
}
