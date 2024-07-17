package service_example.adapters.dtos;

import lombok.Builder;

import java.util.Date;

@Builder
public class PatientDto {
    String name;
    String surname;
    Date dateOfBirth;
}
