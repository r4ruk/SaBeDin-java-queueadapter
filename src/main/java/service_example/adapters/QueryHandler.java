package service_example.adapters;

import sabedin.IQueryHandler;
import sabedin.RequestPostBody;
import service_example.use_case.IPatientService;

import java.util.List;

public class QueryHandler implements IQueryHandler {

    final IPatientService patientService;
    public QueryHandler(IPatientService patientService){
        this.patientService = patientService;
    }

    @Override
    public List<String> handle(RequestPostBody body) {
        return switch (body.getMethod()) {
            case "getall" -> this.patientService.getAll(body.getObject());
            case "getByName" -> this.patientService.getPatient(body.getObject());
            case "getById" -> this.patientService.getPatient(body.getObject());
            default -> List.of();
        };
    }
}
