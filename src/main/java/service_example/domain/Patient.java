package service_example.domain;

import lombok.Value;

import java.util.Date;

@Value
public class Patient {
    String name;
    String surname;
    Date dateOfBirth;
}
